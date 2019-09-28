package com.khinthirisoe.lomotif.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khinthirisoe.lomotif.R
import kotlinx.android.synthetic.main.fragment_detail.view.*

class VideoAdapter(
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private val video: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gallery, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.display(video[position])
    }

    override fun getItemCount() = video.size

    fun setData(video: List<String>){
        this.video.addAll(video)
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun display(video: String) {
            Glide.with(itemView.context)
                .load(video)
                .into(itemView.imageView)

        }

    }
}