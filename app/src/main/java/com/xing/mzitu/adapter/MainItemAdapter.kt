package com.xing.mzitu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.xing.mzitu.R
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.utils.C

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/5
 *
 * version :
 *
 * desc :
 */
class MainItemAdapter : RecyclerView.Adapter<MainItemAdapter.MainItemViewHolder>(){

    private val mDataList = ArrayList<MeiziItem>()

    fun update(list:List<MeiziItem>){
        mDataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        return MainItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item_layout,parent,false))
    }

    override fun getItemCount(): Int = mDataList.size

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        mDataList[position].apply {
            holder.mImageView.load(image_url){
                addHeader(C.ORIGIN_HEADER,C.ORIGIN_VALUE)
                addHeader(C.REFERER_HEADER, C.REFERER_VALUE)
                addHeader(C.USER_AGENT_HEADER,C.USER_AGENT_VALUE)
            }

            holder.mTextView.text = image_desc
        }
    }


    inner class MainItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImageView:ImageView = itemView.findViewById(R.id.id_image_view)
        var mTextView: TextView = itemView.findViewById(R.id.id_tv_info)
    }

}