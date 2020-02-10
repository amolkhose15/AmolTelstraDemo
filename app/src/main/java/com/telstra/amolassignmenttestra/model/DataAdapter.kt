package com.telstra.amolassignmenttestra.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.pojo.ApiData
import kotlinx.android.synthetic.main.recycleview_adapter.view.*


class DataAdapter(mContext: Context, dataModel: List<ApiData>, titlee: Title) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    var mContext: Context
    var dataModel: List<ApiData>
    var title: Title

    public interface Title {
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
        holder.mTxtTitle.text = dataModel.get(position).getTitle()
        holder.mTxtDescription.text = dataModel.get(position).getDescription()

        Picasso.with(mContext).load(dataModel.get(position).getImageHref()).
//            publiclaceholder(R.drawable.ic_launcher_background).
//            error(R.drawable.ic_launcher_background).noFade().
            into(holder.mImageViewData);

//        Glide.with(mContext)
//            .load(dataModel.get(position).getImageHref())
//            .into(holder.mImageViewData)


        if (dataModel.get(position).getTitle() != null) {
            title.gettitle(dataModel.get(position).getTitle()!!)
        }


    }

}