package com.telstra.amolassignmenttestra.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ApiData {
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("description")
    @Expose
    private var description: String? = null
    @SerializedName("imageHref")
    @Expose
    private var imageHref: String? = null

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getImageHref(): String? {
        return imageHref
    }

    fun setImageHref(imageHref: String?) {
        this.imageHref = imageHref
    }
}