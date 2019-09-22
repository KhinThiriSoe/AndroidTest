package com.khinthirisoe.lomotif.ui.gallery

import com.khinthirisoe.lomotif.data.gallery.Hits

/**
 * Result Item - Display in ResultList View Adapter
 */
data class GalleryItem(val url : String) {
    companion object {
        fun from(hits: Hits) = GalleryItem(
            hits.previewURL
        )
    }
}