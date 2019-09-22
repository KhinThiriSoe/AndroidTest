package com.khinthirisoe.lomotif.data.gallery

import com.khinthirisoe.lomotif.data.DataSourceProperties
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryDataSource {

    @GET(DataSourceProperties.API)
    fun getImage(
        @Query(DataSourceProperties.KEY_API_KEY) key: String,
        @Query(DataSourceProperties.KEY_PAGE) page: Int
    ): Single<List<Image>>
}