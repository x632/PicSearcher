package com.poema.unsplash.data



import com.poema.unsplash.BuildConfig
import com.poema.unsplash.data.model.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val AUTH = BuildConfig.UNSPLASH_KEY
    }

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("color") color: String?,
    ): SearchResponseDto


}

