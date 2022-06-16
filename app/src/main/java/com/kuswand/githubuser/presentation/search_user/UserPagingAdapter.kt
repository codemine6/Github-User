package com.kuswand.githubuser.presentation.search_user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kuswand.githubuser.databinding.ItemUserBinding
import com.kuswand.githubuser.domain.model.User

class UserPagingAdapter(val onItemClick: (User) -> Unit) : PagingDataAdapter<User, UserPagingAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)!!

        holder.binding.apply {
            Glide.with(this.root)
                .load(user.avatarUrl)
                .into(civAvatar)

            tvLogin.text = user.login
        }
        holder.itemView.setOnClickListener {
            onItemClick(user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}