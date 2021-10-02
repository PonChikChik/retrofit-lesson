package ru.unit6.course.android.retrofit.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.model.User

class MainAdapter(private val users: ArrayList<UserItems>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_DATABASE = 0
        private const val TYPE_API = 1
    }

    class UserApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(userData: UserItems.UserItemApi) {
            itemView.apply {
                val textViewUserName = findViewById<TextView>(R.id.textViewUserName)
                val textViewUserEmail = findViewById<TextView>(R.id.textViewUserEmail)
                val imageViewAvatar = findViewById<ImageView>(R.id.imageViewAvatar)

                val user = userData.user

                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }
    }

    class UserDBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(userData: UserItems.UserItemDB) {
            itemView.apply {
                val textViewUserName = findViewById<TextView>(R.id.textViewUserName)
                val textViewUserEmail = findViewById<TextView>(R.id.textViewUserEmail)
                val imageViewAvatar = findViewById<ImageView>(R.id.imageViewAvatar)

                val user = userData.user

                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        TYPE_API -> UserApiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
        TYPE_DATABASE -> UserDBViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_database_layout, parent, false))
        else -> throw Exception("view holder not found")
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val user = users[position]) {
            is UserItems.UserItemApi -> (holder as UserApiViewHolder).bind(user)
            is UserItems.UserItemDB -> (holder as UserDBViewHolder).bind(user)
        }
    }

    override fun getItemViewType(position: Int): Int = when (users[position]) {
        is UserItems.UserItemApi -> TYPE_API
        is UserItems.UserItemDB -> TYPE_DATABASE
    }

    fun addUsers(users: List<UserItems>) {
        this.users.apply {
            clear()
            addAll(users)
            notifyDataSetChanged()
        }
    }

    sealed class UserItems {

        data class UserItemApi(val user: User) : UserItems()

        data class UserItemDB(val user: User) : UserItems()
    }
}