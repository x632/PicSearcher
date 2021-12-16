package com.poema.unsplash.ui.viewmodels

import androidx.lifecycle.*
import com.poema.unsplash.data.model.Photo
import com.poema.unsplash.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var job : Job? = null

    private val _listOfPhoto: MutableLiveData<List<Photo>> = MutableLiveData<List<Photo>>()
    val listOfPhoto = _listOfPhoto


    fun searchPhotos(str: String) {

        job?.cancel()
        job = viewModelScope.launch {
            delay(600L)
            val response = repository.searchPhotos(str)
            val list = mutableListOf<Photo>()
            for (element in response.results){
                list.add(element.toPhoto())
            }
            _listOfPhoto.value = list
        }
    }
}