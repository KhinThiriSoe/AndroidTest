package com.khinthirisoe.lomotif.ui.gallery.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.gallery.Hits
import kotlinx.android.synthetic.main.list_gallery.view.*

class GalleryAdapter(
    private val onItemClicked: (Hits) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private val hitList: ArrayList<Hits> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(hitList[position])
    }

    override fun getItemCount() = hitList.size

    fun setData(hits: List<Hits>){
        hitList.addAll(hits)
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.imageView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClicked(hitList[adapterPosition])
                }
            }
        }

        fun bind(hits: Hits) = with(hits) {
            Glide.with(itemView.context)
                .load(previewURL)
                .override(previewWidth, previewHeight)
                .into(itemView.imageView)
        }

    }
}