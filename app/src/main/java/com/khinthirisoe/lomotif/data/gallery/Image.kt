package com.khinthirisoe.lomotif.data.gallery

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<Hits>,
    @SerializedName("total") val total: Int
)