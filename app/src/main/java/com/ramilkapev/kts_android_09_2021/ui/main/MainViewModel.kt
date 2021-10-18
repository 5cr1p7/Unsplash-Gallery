package com.ramilkapev.kts_android_09_2021.ui.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramilkapev.kts_android_09_2021.R
import com.ramilkapev.kts_android_09_2021.networking.Repository
import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.networking.models.User
import com.ramilkapev.kts_android_09_2021.utils.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    private var page = 1
    private val totalList = mutableListOf<Result>()

    private val photosMutableState = MutableLiveData<List<Result>>(emptyList())
    private val usersMutableState = MutableLiveData<List<User>>(emptyList())
    private val likesMutableState = MutableLiveData<Int>()
    private val errorLiveData = SingleLiveEvent<Unit>()
    private val loadingMutableLiveData = MutableLiveData(false)

    val photosState: LiveData<List<Result>>
        get() = photosMutableState

    val usersState: LiveData<List<User>>
        get() = usersMutableState

    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    val likesState: LiveData<Int>
        get() = likesMutableState

    val error: LiveData<Unit>
        get() = errorLiveData

    private val repository = Repository()
    private var currentSearchJob: Job? = null

    fun search(query: String): List<Result> {
        currentSearchJob?.cancel()

        currentSearchJob = viewModelScope.launch {
            runCatching {
                loadingMutableLiveData.postValue(true)
                repository.searchPhotos(query = query, page)
            }.onSuccess {
                if (page == 1) {
                    photosMutableState.postValue(it)
                }
                else {
                    photosMutableState.value.orEmpty() + it
                }
                loadingMutableLiveData.postValue(false)
                totalList.addAll(it ?: emptyList())
                page++
            }.onFailure {
                Timber.e(it)
                onLoadingError()
                photosMutableState.postValue(emptyList())
            }
        }
        return totalList
    }

    fun likesCounter(item: Result) {
        viewModelScope.launch {
            if (!item.likedByUser) {
                item.likedByUser = true
                likesMutableState.value = item.likes++
                repository.likePhoto(item.id)
            } else {
                item.likedByUser = false
                likesMutableState.value = item.likes--
                repository.unlikePhoto(item.id)
            }
        }
    }

    private fun onLoadingError() {
        loadingMutableLiveData.postValue(false)
        errorLiveData.postValue(Unit)
    }

    fun getTotalList() = totalList

}