package com.telstra.amolassignmenttestra.model.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ApiRespose {
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("rows")
    @Expose
    private var rows: List<ApiData?>? = null

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getRows(): List<ApiData?>? {
        return rows
    }

    fun setRows(rows: List<ApiData?>?) {
        this.rows = rows
    }
}