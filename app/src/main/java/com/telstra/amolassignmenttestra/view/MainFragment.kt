package com.telstra.amolassignmenttestra.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.adapter.DataAdapter
import com.telstra.amolassignmenttestra.databinding.ActivityMainBinding
import com.telstra.amolassignmenttestra.room.AppDB
import com.telstra.amolassignmenttestra.room.AppEntity
import com.telstra.amolassignmenttestra.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.view.*


class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var appStoreHomeViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    private var mListener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: String?)
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + getContext()!!.getString(R.string.implemtListener))
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

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)
        appStoreHomeViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.mainModel = appStoreHomeViewModel

        bindview()
        subscribeDataCallBack()
        return binding.root
    }

    private fun subscribeDataCallBack() {

        appStoreHomeViewModel.getProjectList()?.observe(this, Observer<List<AppEntity>> {
            mListener!!.onFragmentInteraction(appStoreHomeViewModel.actionbarName)
            if (it != null) {
                setadapter(it!!)

            }
        })

    }


    private fun bindview() {
        val datalayoutManager = LinearLayoutManager(context)
        datalayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.Recycleview.layoutManager = datalayoutManager
        binding.swapRefreshLayout.isRefreshing = true
        binding.swapRefreshLayout.setOnRefreshListener(this)
        setadapter(
            Room.databaseBuilder(context!!, AppDB::class.java, "TELESTSRA")
                .allowMainThreadQueries()
                .build().appdeo().getallData()
        )


    }

    private fun callToast(message: String) {
        val myToast = Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        )
        myToast.show()
    }

    private fun setadapter(dataList: List<AppEntity>) {
        binding.Recycleview.adapter =
            DataAdapter(
                context!!,
                dataList
            )
        binding.swapRefreshLayout.isRefreshing = false

    }

    fun isNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onRefresh() {
        if (isNetwork(context!!)) {
            subscribeDataCallBack()
        } else {

            setadapter(
                Room.databaseBuilder(context!!, AppDB::class.java, "TELESTSRA")
                    .allowMainThreadQueries()
                    .build().appdeo().getallData()
            )
            callToast(this.getString(R.string.networkMessage))
        }
    }


}