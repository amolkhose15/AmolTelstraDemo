package com.telstra.amolassignmenttestra.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.room.AppEntity
import kotlinx.android.synthetic.main.recycleview_adapter.view.*


class DataAdapter(mContext: Context, dataModel: List<AppEntity>, titlee: Title) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    var mContext: Context
    var dataModel: List<AppEntity>
    var title: Title

    interface Title {
        fun gettitle(title: String)

    }


    init {
        this.mContext = mContext
        this.dataModel = dataModel
        this.title = titlee
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mTxtTitle: TextView = view.TxtTitle
        val mTxtDescription: TextView = view.TxtDescription
        val mImageViewData: ImageView = view.ImageViewData


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_adapter, parent, false)
        return ViewHolder(view)
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
            Glide.with(mContext).load(dataModel[position].imageHref).into(holder.mImageViewData)
            holder.mImageViewData.visibility = VISIBLE
        }


        title.gettitle(dataModel.get(position).title)


    }

}