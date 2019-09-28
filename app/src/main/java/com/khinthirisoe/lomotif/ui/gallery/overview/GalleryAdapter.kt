package com.khinthirisoe.lomotif.ui.gallery.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.gallery.Hits
import kotlinx.android.synthetic.main.fragment_detail.view.*

class GalleryAdapter(
    private val onClick: (Hits) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private val hitList: ArrayList<Hits> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.display(hitList[position])
    }

    override fun getItemCount() = hitList.size

    fun setData(hits: List<Hits>){
        hitList.addAll(hits)
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun display(hits: Hits) {
            Glide.with(itemView.context)
                .load(hits.previewURL)
                .override(hits.previewWidth, hits.previewHeight)
                .into(itemView.imageView)

            itemView.imageView.setOnClickListener { onClick(hits) }
        }

    }
}