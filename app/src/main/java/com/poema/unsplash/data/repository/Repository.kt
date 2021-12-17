package com.poema.unsplash.data.repository


import com.poema.unsplash.data.model.SearchResponseDto


interface Repository {
    suspend fun searchPhotos(query : String): SearchResponseDto
}