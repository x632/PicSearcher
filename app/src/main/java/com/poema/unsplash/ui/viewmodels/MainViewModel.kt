package com.poema.unsplash.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.poema.unsplash.data.UnSplashApi
import com.poema.unsplash.data.repository.Repository
import com.poema.unsplash.ui.uimodel.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.broadcastIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var searchText: MutableLiveData<String> = MutableLiveData("sunset")
    private var job: Job? = null

    val listOfPhoto = Transformations.switchMap(searchText) {
        repository.getSearchResults(it).cachedIn(viewModelScope).asLiveData()
    }

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


    fun setSearchText(str: String) {
        searchText.value = str
    }
}

