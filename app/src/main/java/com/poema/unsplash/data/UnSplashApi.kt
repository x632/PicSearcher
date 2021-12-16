package com.poema.unsplash.data


import com.poema.unsplash.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val AUTH = "yTk5ZpGoiPgLxhpBM_X6sv9Ed6oITJ4MEoYcG_EhwXQ"
    }

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): SearchResponse

}

