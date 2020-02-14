package com.telstra.amolassignmenttestra.model.pojo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.telstra.amolassignmenttestra.R


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