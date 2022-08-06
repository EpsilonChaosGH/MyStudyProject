package com.example.mystudyproject.recyclerView

import android.app.usage.UsageEvents.Event.NONE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.ItemUserBinding

interface UserActionListener {
    fun onUserMove(user: User, moveBy: Int)
    fun onUserDelete(user: User)
    fun onUserDetails(user: User)
}

class UsersAdapter(
    private val actionListener: UserActionListener
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener {
    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var users: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onClick(v: View) {
        val user = v.tag as User
        when (v.id) {
            R.id.imageViewMore -> {
                showPopupMenu(v)
            }
            else -> {
                actionListener.onUserDetails(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.imageViewMore.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]

        holder.itemView.tag = user
        holder.binding.imageViewMore.tag = user

        holder.binding.userNameTextView.text = user.name
        holder.binding.UserCompanyTextView.text = user.company

        if (user.photo.isNotBlank()) {
            Glide.with(holder.binding.imageViewUser.context)
                .load(user.photo)
                .circleCrop()
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .into(holder.binding.imageViewUser)
        } else {
            holder.binding.imageViewUser.setImageResource(R.drawable.ic_user)
        }

    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val user = view.tag as User
        val position = users.indexOfFirst { it.id == user.id }

        popupMenu.menu.add(0, ID_MOVE_UP, NONE, context.getString(R.string.move_up)).apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, NONE, context.getString(R.string.move_down)).apply {
            isEnabled = position < users.size - 1
        }
        popupMenu.menu.add(0, ID_REMOVE, NONE, context.getString(R.string.remove))

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> {
                    actionListener.onUserMove(user, -1)
                }
                ID_MOVE_DOWN -> {
                    actionListener.onUserMove(user, 1)
                }
                ID_REMOVE -> {
                    actionListener.onUserDelete(user)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    override fun getItemCount() = users.size

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
    }
}

