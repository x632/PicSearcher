package com.poema.unsplash.ui.viewmodels

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.poema.unsplash.data.UnSplashApi
import com.poema.unsplash.data.repository.Repository
import com.poema.unsplash.ui.UiEvent
import com.poema.unsplash.ui.uimodel.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var color: String? = null
    var searchText: MutableLiveData<String> = MutableLiveData<String>("sunset")



    /*private val _uiState = MutableStateFlow<UiState>(UiState.SearchQuery("sunset"))
    val uiState: StateFlow<UiState> = _uiState*/
    val listOfPhoto = switchMap(searchText) { query ->
     repository.getSearchResults(query, color).cachedIn(viewModelScope).asLiveData()}







/*    viewModelScope.launch {
        // uiState.collect {
        listOfPhoto = when (it) {
            is UiState.SearchQuery -> {
                repository.getSearchResults(searchText,color)
                    .cachedIn(viewModelScope).asLiveData()
            }
            is UiState.SearchColor -> {
                repository.getSearchResults(searchText, color)
                    .cachedIn(viewModelScope).asLiveData()
            }
        }
        // }
    }*/


//val listOfPhoto = switchMap(searchText) { query ->
//     repository.getSearchResults(query, color.value).cachedIn(viewModelScope).asLiveData()
//}

    //fun onEvent(event: UiEvent) = when (event) {
   /* fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.ColorFilter -> {
                println("!!! BEEN IN UI EVENT COLORFILTER")
                if (event.col == "no filter") {
                    //color.value = null
                    _uiState.value = UiState.SearchColor(null)

                } else {
                    // color.value = event.col
                    _uiState.value = UiState.SearchColor(event.col)
                }
            }
            is UiEvent.SearchQuery -> {
                println("!!! BEEN IN UI EVENT SEARCHQUERY")
                _uiState.value = UiState.SearchQuery(event.query)
            }
        }
    }*/


/*private fun getSearchResult(str: String):MutableLiveData<List<Photo>> {
    val mutList: MutableLiveData<List<Photo>> = MutableLiveData<<List<Photo>>()
    job?.cancel()
    job = viewModelScope.launch {
        delay(600L)
        val response = repository.getSearchResults(str)
        val list = mutableListOf<Photo>()
        for (element in response.results.toPhoto()){
            list.add(element.toPhoto())
        }
        mutList.value = list
    }
    return mutList
}*/

    fun setColor(str: String) {
        color = str
    }

    fun setSearchText(str: String) {
        searchText.value = str
    }
}

sealed class UiState {
    data class SearchQuery(val query: String) : UiState()
    data class SearchColor(val col: String?) : UiState()
}
