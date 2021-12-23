package com.poema.unsplash.data.repository



import androidx.paging.PagingData
import com.poema.unsplash.data.model.SearchResponseDto
import com.poema.unsplash.ui.uimodel.Photo
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun searchPhotos(query : String): SearchResponseDto

    fun searchPhotosByColor(color : String): Flow<PagingData<Photo>>


    fun getSearchResults(query: String) : Flow<PagingData<Photo>>
}