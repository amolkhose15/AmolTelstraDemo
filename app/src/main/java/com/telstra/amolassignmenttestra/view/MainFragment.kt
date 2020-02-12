package com.telstra.amolassignmenttestra.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.adapter.DataAdapter
import com.telstra.amolassignmenttestra.databinding.ActivityMainBinding
import com.telstra.amolassignmenttestra.room.AppDB
import com.telstra.amolassignmenttestra.room.AppEntity
import com.telstra.amolassignmenttestra.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.view.*


class MainFragment : Fragment() {

    private lateinit var appStoreHomeViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    var dataList: List<AppEntity> = ArrayList()
    private var mListener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: String?)
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement ")
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

        setRecyclerView(dataList)
        subscribeDataCallBack()
        return binding.root
    }

    private fun subscribeDataCallBack() {

        appStoreHomeViewModel.getProjectList()?.observe(this, Observer<List<AppEntity>> {
            mListener!!.onFragmentInteraction(appStoreHomeViewModel.actionbarName)
            if (it != null) {
                setadapter(it!!)
                binding.swapRefreshLayout.isRefreshing = false
            }
        })

    }


    private fun setRecyclerView(dataList: List<AppEntity>) {
        val datalayoutManager = LinearLayoutManager(context)
        datalayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.Recycleview.layoutManager = datalayoutManager
        binding.swapRefreshLayout.isRefreshing = true
        binding.swapRefreshLayout.setOnRefreshListener {
            if (isNetwork(context!!)) {
                subscribeDataCallBack()
            } else {

                setadapter(
                    Room.databaseBuilder(context!!, AppDB::class.java, "TELESTSRA")
                        .allowMainThreadQueries()
                        .build().appdeo().getallData()
                )


                binding.swapRefreshLayout.isRefreshing = false
            }
        }
        setadapter(dataList)


    }

    private fun setadapter(dataList: List<AppEntity>) {
        binding.Recycleview.adapter =
            DataAdapter(
                context!!,
                dataList
            )
    }

    fun isNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


}