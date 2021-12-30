package com.poema.unsplash.ui.viewmodels

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.cachedIn
import com.poema.unsplash.data.repository.Repository
import com.poema.unsplash.other.Constants.DEFAULT_SEARCH
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    /*private val _uiState = MutableStateFlow<UiState>(UiState.SearchQuery("sunset"))
    val uiState: StateFlow<UiState> = _uiState*/

    private val searchText: MutableLiveData<String> = MutableLiveData<String>(DEFAULT_SEARCH)
    var currentSearch = ""
    private var col: String? = null

    val listOfPhoto = switchMap(searchText) { query ->
        repository.getSearchResults(query, col).cachedIn(viewModelScope).asLiveData()
    }

    fun setColor(str: String) {
        col = if (str == "no filter") {
            null
        } else str
    }

    fun setSearchText(str: String) {
        searchText.value = str
    }
}
/*
viewModelScope.launch {
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

}
*/

/* fun onEvent(event: UiEvent) {
     when (event) {
         is UiEvent.ColorFilter -> {
             if (event.col == "no filter") {
                 _uiState.value = UiState.SearchColor(null)

             } else {
                 // color.value = event.col
                 _uiState.value = UiState.SearchColor(event.col)
             }
         }
         is UiEvent.SearchQuery -> {
             _uiState.value = UiState.SearchQuery(event.query)
         }
     }
 }*/


/*sealed class UiState {
    data class SearchQuery(val query: String) : UiState()
    data class SearchColor(val col: String?) : UiState()
}*/
