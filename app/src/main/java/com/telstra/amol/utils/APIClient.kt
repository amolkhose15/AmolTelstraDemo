package com.telstra.amolassignmenttestra.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    companion object {

        val baseURL: String = "https://dl.dropboxusercontent.com/"
        var retofit: Retrofit? = null

        val client: Retrofit
            get() {

                if (retofit == null) {
                    retofit = Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retofit!!
            }
    }
}