package com.ramilkapev.kts_android_09_2021.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.ramilkapev.kts_android_09_2021.R
import com.ramilkapev.kts_android_09_2021.networking.models.Result

class ImageItemDelegate(
    private val onIncreaseLike: (Result) -> Unit,
    private val onClick: (Result) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, ImageItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is Result
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return ViewHolder(itemView, onIncreaseLike)
    }

    override fun onBindViewHolder(
        item: Any,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item as Result)
    }

    inner class ViewHolder(
        private val view: View,
        private val onIncreaseLike: (Result) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private var currentItem: Result? = null
        private var avatar: ImageView = view.findViewById(R.id.avatarIv)
        private var name: TextView = view.findViewById(R.id.name)
        private var likes: TextView = view.findViewById(R.id.likesTv)
        private var image: ImageView = view.findViewById(R.id.image)
        private var likeBtn: ImageButton = view.findViewById(R.id.likeBtn)

        init {
            image.setOnClickListener { currentItem?.let (onClick) }
            likeBtn.setOnClickListener { currentItem?.let(onIncreaseLike) }
        }

        fun bind(item: Result) = with(view) {
            currentItem = item
            Glide.with(this).load(item.user.profileImage.medium).into(avatar)
            name.text = item.user.name
            Glide.with(this).load(item.urls.regular).into(image)
            if (item.likedByUser) {
                likeBtn.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.outline_favorite_black_24,
                        null
                    )
                )
            } else {
                likeBtn.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.outline_favorite_border_24,
                        null
                    )
                )
            }
            likes.text = item.likes.toString()
        }
    }
}