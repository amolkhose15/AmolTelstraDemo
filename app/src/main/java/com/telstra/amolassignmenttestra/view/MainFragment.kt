package com.telstra.amolassignmenttestra.view

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.model.DataAdapter
import com.telstra.amolassignmenttestra.model.pojo.ApiRespose
import com.telstra.amolassignmenttestra.room.AppDB
import com.telstra.amolassignmenttestra.room.AppEntity
import com.telstra.amolassignmenttestra.utils.APIClient
import com.telstra.amolassignmenttestra.utils.ApiInterface
import kotlinx.android.synthetic.main.frgment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, DataAdapter.Title {

    lateinit var adapter: DataAdapter
    lateinit var mContext: Context
    lateinit var mRecycleview: RecyclerView
    lateinit var mswapRefreshLayout: SwipeRefreshLayout

    private var mListener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: String?)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = layoutInflater.inflate(R.layout.frgment_main, container, false)
        bindview(view)
        return view
    }


    private fun bindview(view: View) {
        mContext = context!!
        mRecycleview = view.Recycleview
        mswapRefreshLayout = view.swapRefreshLayout
        mRecycleview.layoutManager = LinearLayoutManager(mContext)
        mswapRefreshLayout.setOnRefreshListener(this)




        if (isNetwork(mContext)) {
            callAPI()
        } else {
            val toast = Toast.makeText(mContext, "Network Not Available", Toast.LENGTH_LONG)
            toast.show()


            var list = Room.databaseBuilder(mContext, AppDB::class.java, "TELESTSRA")
                .allowMainThreadQueries()
                .build().appdeo().getAllBooks()
            if (list.size > 0) {
                callAdapter(list)
            }


        }


    }

    fun isNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    private fun callAPI() {
        val mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setMessage("Loading...")
        mProgressDialog.show()
        var apiServices = APIClient.client.create(ApiInterface::class.java)
        val call = apiServices.getData()

        call.enqueue(object : Callback<ApiRespose> {
            override fun onResponse(call: Call<ApiRespose>, response: Response<ApiRespose>) {
                if (mProgressDialog.isShowing) {
                    mProgressDialog.dismiss()
                }

                Room.databaseBuilder(mContext, AppDB::class.java, "TELESTSRA")
                    .allowMainThreadQueries()
                    .build().appdeo().delete()
                mListener!!.onFragmentInteraction(response.body()!!.getTitle())
                for (apidata in response.body()!!.getRows()!!) {
                    Room.databaseBuilder(mContext, AppDB::class.java, "TELESTSRA")
                        .allowMainThreadQueries()
                        .build().appdeo().saveBooks(
                            AppEntity(
                                title = apidata!!.getTitle() ?: "",
                                description = apidata!!.getDescription() ?: "",
                                imageHref = apidata!!.getImageHref() ?: ""
                            )
                        )
                }

                callAdapter(
                    Room.databaseBuilder(mContext, AppDB::class.java, "TELESTSRA")
                        .allowMainThreadQueries()
                        .build().appdeo().getAllBooks()
                )
                mswapRefreshLayout.isRefreshing = false
            }


            override fun onFailure(call: Call<ApiRespose>?, t: Throwable?) {
                if (mProgressDialog.isShowing) {
                    mProgressDialog.dismiss()
                }

            }
        })

    }

    private fun callAdapter(list: List<AppEntity>) {
        adapter = DataAdapter(
            mContext,
            list,
            this@MainFragment
        )
        mRecycleview.adapter = adapter;

    }
    override fun onRefresh() {
        if (isNetwork(mContext)) {
            callAPI()
        } else {
            mswapRefreshLayout.isRefreshing = false
        }
    }

    override fun gettitle(title: String) {
    }


}