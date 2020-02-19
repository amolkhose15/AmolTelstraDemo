package com.telstra.amolassignmenttestra.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.room.AppEntity
import kotlinx.android.synthetic.main.recycleview_adapter.view.*


class DataAdapter(mContext: Context, dataModel: List<AppEntity>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    var mContext: Context
    var dataModel: List<AppEntity>

    init {
        this.mContext = mContext
        this.dataModel = dataModel
       }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mTxtTitle: TextView = view.TxtTitle
        val mTxtDescription: TextView = view.TxtDescription
        val mImageViewData: ImageView = view.ImageViewData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_adapter, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return dataModel.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTxtTitle.text = dataModel[position].title
        holder.mTxtDescription.text = dataModel[position].description
        if (dataModel[position].imageHref.isEmpty()) {
            holder.mImageViewData.visibility = GONE
        } else {
            Glide.with(mContext)
                .load(dataModel[position].imageHref)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        p0: GlideException?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: Boolean
                    ): Boolean {
                        holder.mImageViewData.visibility = GONE
                        return true
                    }

                    override fun onResourceReady(
                        p0: Drawable?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: DataSource?,
                        p4: Boolean
                    ): Boolean {
                        holder.mImageViewData.visibility = VISIBLE
                        return false
                    }
                })
                .into(holder.mImageViewData)

        }

    }

    // notifyData list
    fun updateData(getallData: List<AppEntity>) {
        this.dataModel = getallData;
        notifyDataSetChanged()
    }


}

