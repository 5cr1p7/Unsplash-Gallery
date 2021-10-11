package com.ramilkapev.kts_android_09_2021.ui.main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.networking.models.User

class ImagesAdapter(
    onIncreaseLike: (item: Result) -> Unit,
    onClick: (item: Result) -> Unit
): AsyncListDifferDelegationAdapter<Any>(DiffCallback()) {

    init {
        delegatesManager
            .addDelegate(ImageItemDelegate(onIncreaseLike, onClick))
            .addDelegate(UserItemDelegate())
            .addDelegate(PageLoadingAdapterDelegate())
    }

    class DiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is User -> first.id == (second as User).id
                is Result -> first.id == (second as Result).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }
}