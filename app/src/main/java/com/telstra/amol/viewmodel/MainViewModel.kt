package com.telstra.amolassignmenttestra.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.telstra.amolassignmenttestra.model.pojo.ApiRespose
import com.telstra.amolassignmenttestra.room.AppDB
import com.telstra.amolassignmenttestra.room.AppEntity
import com.telstra.amolassignmenttestra.utils.APIClient
import com.telstra.amolassignmenttestra.utils.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {

    var appDB = AppDB.getInstance(application)
    var myList: MutableList<AppEntity> = mutableListOf<AppEntity>()
    lateinit var apiResponse: APiResponse;

    interface APiResponse {
        fun apiStatus(
            b: List<AppEntity>,
            b1: Boolean
        )

    }

    fun apistatus(apiResponse: APiResponse) {
        this.apiResponse = apiResponse;
    }

    //    API Call and Return the data
    fun getProjectList(): List<AppEntity> {
        APIClient.client.create(ApiInterface::class.java).getData()
            .enqueue(object : Callback<ApiRespose> {
                override fun onResponse(call: Call<ApiRespose>, response: Response<ApiRespose>) {
                    if (response.isSuccessful) {
                        for (apidata in response.body()!!.getRows()!!) {
                            myList.add(
                                AppEntity(
                                    title = apidata!!.getTitle() ?: "",
                                    description = apidata!!.getDescription() ?: "",
                                    imageHref = apidata!!.getImageHref() ?: ""
                                )
                            )
                        }
                        insertOrUpdate(myList)
                    }
                }
                override fun onFailure(call: Call<ApiRespose>?, t: Throwable?) {
                    apiResponse.apiStatus(appDB.getallData(), false)
                }
            })
        return myList;
    }

    fun insertOrUpdate(item: List<AppEntity>) {
        if (appDB.getallData().isEmpty()) {
            appDB.insertData(item)
            } else {
            appDB.update(item)
        }
        apiResponse.apiStatus(appDB.getallData(), true)
    }
}