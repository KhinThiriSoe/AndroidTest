package com.khinthirisoe.lomotif.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khinthirisoe.lomotif.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_media.*


class VideoAdapter(private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private val media: ArrayList<MediaModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_media, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.display(media[position])
    }

    override fun getItemCount() = media.size

    fun setData(video: List<MediaModel>){
        this.media.addAll(video)
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            imageView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClicked(media[adapterPosition].video)
                }
            }
        }

        fun display(mediaModel: MediaModel) {

            Glide.with(itemView.context)
                .load(mediaModel.video)
                .placeholder(R.drawable.ic_audiotrack_black)
                .into(imageView)

            txtTitle.text = mediaModel.title
        }

    }
}