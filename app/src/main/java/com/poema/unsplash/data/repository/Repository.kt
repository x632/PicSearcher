package com.poema.unsplash.data.repository


import com.poema.unsplash.data.model.SearchResponse


interface Repository {
    suspend fun searchPhotos(query : String): SearchResponse
}