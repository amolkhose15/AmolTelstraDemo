package com.telstra.amolassignmenttestra.utils

import com.telstra.amolassignmenttestra.model.pojo.ApiRespose
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    //    Base USL
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getData(): Call<ApiRespose>


}