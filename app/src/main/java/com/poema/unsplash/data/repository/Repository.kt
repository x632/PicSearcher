package com.poema.unsplash.data.repository



import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.poema.unsplash.ui.uimodel.Photo
import kotlinx.coroutines.flow.Flow


interface Repository {

    fun getSearchResults(query: String, color: String?) : Flow<PagingData<Photo>>
}