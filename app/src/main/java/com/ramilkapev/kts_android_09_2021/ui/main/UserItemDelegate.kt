package com.ramilkapev.kts_android_09_2021.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.ramilkapev.kts_android_09_2021.R
import com.ramilkapev.kts_android_09_2021.networking.models.User

class UserItemDelegate : AbsListItemAdapterDelegate<Any, Any, UserItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is User
    }

    override fun onCreateViewHolder(parent: ViewGroup): UserItemDelegate.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        item: Any,
        holder: UserItemDelegate.ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item as User)
    }

    inner class ViewHolder(
        private val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private var currentItem: User? = null
        private var userAvatar: ImageView = containerView.findViewById(R.id.userAvatar)
        private var userName: TextView = containerView.findViewById(R.id.userName)
        private var nickName: TextView = containerView.findViewById(R.id.nickName)
        private var firstUserImage: ImageView = containerView.findViewById(R.id.firstUserImage)
        private var secondUserImage: ImageView = containerView.findViewById(R.id.secondUserImage)
        private var thirdUserImage: ImageView = containerView.findViewById(R.id.thirdUserImage)

        fun bind(item: User) = with(containerView) {
            currentItem = item
            Glide.with(this).load(item.profileImage?.medium).centerCrop().into(userAvatar)
            userName.text = item.name
            nickName.text = item.username
            Glide.with(this).load(item.profileImage?.medium).into(firstUserImage)
            Glide.with(this).load(item.profileImage?.medium).into(secondUserImage)
            Glide.with(this).load(item.profileImage?.medium).into(thirdUserImage)
        }
    }
}