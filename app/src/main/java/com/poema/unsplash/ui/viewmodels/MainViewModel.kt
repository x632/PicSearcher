package com.poema.unsplash.ui.viewmodels

import androidx.lifecycle.*
import com.poema.unsplash.ui.uimodel.Photo
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

    var searchText : MutableLiveData<String> = MutableLiveData("sunset")
    private var job : Job? = null

    val listOfPhoto: LiveData<List<Photo>> = Transformations.switchMap(searchText){
        searchPhotos(it)
    }

    fun searchPhotos(str: String):MutableLiveData<List<Photo>> {

        val mutList: MutableLiveData<List<Photo>> = MutableLiveData<List<Photo>>()
        job?.cancel()
        job = viewModelScope.launch {
            delay(600L)
            val response = repository.searchPhotos(str)
            val list = mutableListOf<Photo>()
            var x = 0
            for (element in response.results){
                list.add(element.toPhoto())
            }
            mutList.value = list
        }
        return mutList
    }

    fun setSearchText(str:String){
        searchText.value = str
    }
}