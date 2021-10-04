package com.ramilkapev.kts_android_09_2021.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramilkapev.kts_android_09_2021.networking.Repository
import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.networking.models.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    private var page = 1
    private val totalList = mutableListOf<Result>()

    private val photosMutableState = MutableLiveData<List<Result>>(emptyList())
    val photosState: LiveData<List<Result>>
        get() = photosMutableState

    private val usersMutableState = MutableLiveData<List<User>>(emptyList())
    val usersState: LiveData<List<User>>
        get() = usersMutableState

    private val likesMutableState = MutableLiveData<Int>()
    val likesState: LiveData<Int>
        get() = likesMutableState

    private val repository = Repository()
    private var currentSearchJob: Job? = null

    fun search(query: String): List<Result> {
        currentSearchJob?.cancel()

        currentSearchJob = viewModelScope.launch {
            runCatching {
                repository.searchPhotos(query = query, page)
            }.onSuccess {
                photosMutableState.postValue(it)
                totalList.addAll(it ?: emptyList())
                page++
            }.onFailure {
                Timber.e(it)
                photosMutableState.postValue(emptyList())
            }
        }
        return totalList
    }

    fun likesCounter(item: Result) {
        if (!item.likedByUser) {
            item.likedByUser = true
            likesMutableState.value = item.likes++
        } else {
            item.likedByUser = false
            likesMutableState.value = item.likes--
        }
    }

    fun getTotalList() = totalList

}