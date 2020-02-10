package com.telstra.amolassignmenttestra.utils

import com.telstra.amolassignmenttestra.pojo.ApiRespose
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getData(): Call<ApiRespose>


}