package com.telstra.amolassignmenttestra.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, DataAdapter.Title {

    lateinit var adapter: DataAdapter
    lateinit var mContext: Context
    lateinit var mRecycleview: RecyclerView
    lateinit var mswapRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindview();


    }

    private fun bindview() {
        mContext = this@MainActivity
        mRecycleview = findViewById(R.id.Recycleview)
        mswapRefreshLayout = findViewById(R.id.swapRefreshLayout)
        mRecycleview.layoutManager = LinearLayoutManager(mContext)

        mswapRefreshLayout.setOnRefreshListener(this)
        callAPI()


    }

    private fun callAPI() {
        var apiServices = APIClient.client.create(ApiInterface::class.java)
        val call = apiServices.getData()

        call.enqueue(object : Callback<ApiRespose> {
            override fun onResponse(call: Call<ApiRespose>, response: Response<ApiRespose>) {

                adapter = DataAdapter(
                    mContext,
                    response.body()?.getRows() as List<ApiData>,
                    this@MainActivity
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
        val actionBar = supportActionBar
        actionBar!!.title = title


    }
}