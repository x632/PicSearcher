package com.poema.unsplash.ui.viewmodels

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.cachedIn
import com.poema.unsplash.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val searchText: MutableLiveData<String> = MutableLiveData<String>("sunset")

    var col:String? = null
    /*private val _uiState = MutableStateFlow<UiState>(UiState.SearchQuery("sunset"))
    val uiState: StateFlow<UiState> = _uiState*/
    val listOfPhoto = switchMap(searchText) { query ->
     repository.getSearchResults(query, col).cachedIn(viewModelScope).asLiveData()}

   /* var listOfPhoto2 = switchMap(color) { color ->
        repository.getSearchResults(searchText.value!!, color).cachedIn(viewModelScope).asLiveData()}
*/
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
        col = if(str == "no filter"){
            null
        } else str
    }

    fun setSearchText(str: String) {
        searchText.value = str
    }
}

/*sealed class UiState {
    data class SearchQuery(val query: String) : UiState()
    data class SearchColor(val col: String?) : UiState()
}*/
