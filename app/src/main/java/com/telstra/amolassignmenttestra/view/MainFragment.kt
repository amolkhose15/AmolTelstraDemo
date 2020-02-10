package com.telstra.amolassignmenttestra.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.model.DataAdapter
import com.telstra.amolassignmenttestra.pojo.ApiData
import com.telstra.amolassignmenttestra.pojo.ApiRespose
import com.telstra.amolassignmenttestra.utils.APIClient
import com.telstra.amolassignmenttestra.utils.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, DataAdapter.Title {

    lateinit var adapter: DataAdapter
    lateinit var mContext: Context
    lateinit var mRecycleview: RecyclerView
    lateinit var mswapRefreshLayout: SwipeRefreshLayout
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
        mRecycleview = view.findViewById(R.id.Recycleview)
        mswapRefreshLayout = view.findViewById(R.id.swapRefreshLayout)
        mRecycleview.layoutManager = LinearLayoutManager(mContext)

        mswapRefreshLayout.setOnRefreshListener(this)
        callAPI()


    }



    fun getInstance(): MainFragment? {
        val fragment = MainFragment()
        val args = Bundle()
        fragment.setArguments(args)
        return fragment
    }

    private fun callAPI() {
        var apiServices = APIClient.client.create(ApiInterface::class.java)
        val call = apiServices.getData()

        call.enqueue(object : Callback<ApiRespose> {
            override fun onResponse(call: Call<ApiRespose>, response: Response<ApiRespose>) {

                adapter = DataAdapter(
                    mContext,
                    response.body()?.getRows() as List<ApiData>,
                    this@MainFragment
                )

                mRecycleview.adapter = adapter;
                mswapRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<ApiRespose>?, t: Throwable?) {
                Log.d("ERROR : ", " ")

            }
        })

    }

    override fun onRefresh() {
        callAPI()
    }

    override fun gettitle(title: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}