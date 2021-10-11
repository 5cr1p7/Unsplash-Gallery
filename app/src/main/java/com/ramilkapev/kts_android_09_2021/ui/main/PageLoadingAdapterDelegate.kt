package com.ramilkapev.kts_android_09_2021.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.ramilkapev.kts_android_09_2021.R

class PageLoadingAdapterDelegate: AbsListItemAdapterDelegate<LoadingItem, Any, PageLoadingAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is LoadingItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.loading_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        item: LoadingItem,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
    }

    class ViewHolder(
        containerView: View
    ) : RecyclerView.ViewHolder(containerView)

}