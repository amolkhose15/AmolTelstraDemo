package com.telstra.amolassignmenttestra.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.telstra.amolassignmenttestra.model.pojo.ApiRespose
import com.telstra.amolassignmenttestra.room.AppDB
import com.telstra.amolassignmenttestra.room.AppEntity
import com.telstra.amolassignmenttestra.utils.APIClient
import com.telstra.amolassignmenttestra.utils.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mutablePostList: MutableLiveData<List<AppEntity>> = MutableLiveData()
    lateinit var actionbarName: String
    var appDB = Room.databaseBuilder(getApplication(), AppDB::class.java, "TELESTSRA")
        .allowMainThreadQueries()
        .build().appdeo()

    fun getProjectList(): LiveData<List<AppEntity>>? {

        var apiServices = APIClient.client.create(ApiInterface::class.java)
        val call = apiServices.getData()

        call.enqueue(object : Callback<ApiRespose> {
            override fun onResponse(call: Call<ApiRespose>, response: Response<ApiRespose>) {
                actionbarName = response.body()!!.getTitle()!!
                appDB.delete()
                for (apidata in response.body()!!.getRows()!!) {
                    appDB.insertData(
                        AppEntity(
                            title = apidata!!.getTitle() ?: "",
                            description = apidata!!.getDescription() ?: "",
                            imageHref = apidata!!.getImageHref() ?: ""
                        )
                    )
                }
                mutablePostList.postValue(appDB.getallData())
            }

            override fun onFailure(call: Call<ApiRespose>?, t: Throwable?) {

            }
        })

        return mutablePostList


    }

}