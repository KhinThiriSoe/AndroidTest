package com.khinthirisoe.lomotif.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.gallery.Hits
import kotlinx.android.synthetic.main.list_gallery.view.*

class GalleryAdapter(
    var list: List<Hits>
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.display(list[position])
    }

    override fun getItemCount() = list.size

    inner class GalleryViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun display(hits: Hits) {
            Glide.with(itemView.context).load(hits.previewURL).into(itemView.imageView)
        }

    }
}