package com.khinthirisoe.lomotif.ui.gallery.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.gallery.Hits
import kotlinx.android.synthetic.main.item_gallery.view.*
import kotlin.properties.Delegates

class GalleryAdapter(
    private val onItemClicked: (Hits) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    var hitList by Delegates.observable(listOf<Hits>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(hitList[position])
    }

    override fun getItemCount() = hitList.size

    inner class GalleryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.imageView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClicked(hitList[adapterPosition])
                }
            }
        }

        fun bind(hits: Hits) = with(hits) {
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .signature(ObjectKey(System.currentTimeMillis()))
            Glide.with(itemView.context)
                .load(previewURL)
                .apply(options)
                .override(previewWidth, previewHeight)
                .into(itemView.imageView)
        }

    }
}