package com.ramilkapev.kts_android_09_2021

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.ramilkapev.kts_android_09_2021.networking.Repository
import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(): ViewModel() {

    private val repository = Repository()
    private val photoInfoLiveData = MutableLiveData<Result>()
    private val likesMutableState = MutableLiveData<Int>()
    private val loadingMutableLiveData = MutableLiveData(false)
    private val errorLiveData = SingleLiveEvent<Unit>()

    val photoInfo: LiveData<Result>
        get() = photoInfoLiveData

    val likesState: LiveData<Int>
        get() = likesMutableState

    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    val error: LiveData<Unit>
        get() = errorLiveData

    fun getPhotoInfo(id: String) {
        viewModelScope.launch {
            loadingMutableLiveData.postValue(true)
            val photo = repository.getPhotoById(id)
            if (photo != null) {
                loadingMutableLiveData.postValue(false)
                photoInfoLiveData.postValue(photo)
            } else {
                onLoadingError()
            }
        }
    }

    fun likesCounter(id: String) {
        viewModelScope.launch {
            val photo = repository.getPhotoById(id)
            if (photo == null) {
                onLoadingError()
                return@launch
            }
            else {
                photoInfoLiveData.postValue(photo)
            }
            if (!photo.likedByUser) {
                photo.likedByUser = true
                likesMutableState.value = photo.likes++
                repository.likePhoto(photo.id)
            } else {
                photo.likedByUser = false
                likesMutableState.value = photo.likes--
                repository.unlikePhoto(photo.id)
            }
        }
    }

    private fun onLoadingError() {
        loadingMutableLiveData.postValue(false)
        errorLiveData.postValue(Unit)
    }
}