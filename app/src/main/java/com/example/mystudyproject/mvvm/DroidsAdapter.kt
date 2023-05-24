package com.example.mystudyproject.mvvm

import android.app.usage.UsageEvents.Event.NONE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.ItemDroidBinding

interface DroidActionListener {
    fun onDroidMove(droid: Droid, moveBy: Int)

    fun onDroidDelete(droid: Droid)

    fun onDroidDetails(droid: Droid)
}

class DroidsDiffCallback(
    private val oldList: List<DroidListItem>,
    private val newList: List<DroidListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDroid = oldList[oldItemPosition]
        val newDroid = newList[newItemPosition]
        return oldDroid.droid.id == newDroid.droid.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDroid = oldList[oldItemPosition]
        val newDroid = newList[newItemPosition]
        return oldDroid == newDroid
    }
}

class DroidsAdapter(
    private val actionListener: DroidActionListener
) : RecyclerView.Adapter<DroidsAdapter.DroidsViewHolder>(), View.OnClickListener {
    class DroidsViewHolder(
        val binding: ItemDroidBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var droids: List<DroidListItem> = emptyList()
        set(newValue) {
            val diffCallback = DroidsDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(v: View) {
        val droid = v.tag as Droid
        when (v.id) {
            R.id.imageViewMore -> {
                showPopupMenu(v)
            }

            else -> {
                actionListener.onDroidDetails(droid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DroidsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDroidBinding.inflate(inflater, parent, false)

        binding.imageViewMore.setOnClickListener(this)

        return DroidsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DroidsViewHolder, position: Int) {

        val droidListItem = droids[position]
        val droid = droidListItem.droid

        holder.itemView.tag = droid
        holder.binding.imageViewMore.tag = droid

        if (droidListItem.isInProgress) {
            holder.binding.imageViewMore.visibility = View.INVISIBLE
            holder.binding.progressBar.visibility = View.VISIBLE
            holder.binding.root.setOnClickListener(null)
        } else {
            holder.binding.imageViewMore.visibility = View.VISIBLE
            holder.binding.progressBar.visibility = View.GONE
            holder.binding.root.setOnClickListener(this@DroidsAdapter)
        }

        holder.binding.droidNameTextView.text = droid.name
        holder.binding.droidCompanyTextView.text = droid.company

        if (droid.photo.isNotBlank()) {
            Glide.with(holder.binding.imageViewDroid.context)
                .load(droid.photo)
                .circleCrop()
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .into(holder.binding.imageViewDroid)
        } else {
            holder.binding.imageViewDroid.setImageResource(R.drawable.ic_user)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val droid = view.tag as Droid
        val position = droids.indexOfFirst { it.droid.id == droid.id }

        popupMenu.menu.add(0, ID_MOVE_UP, NONE, context.getString(R.string.move_up)).apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, NONE, context.getString(R.string.move_down)).apply {
            isEnabled = position < droids.size - 1
        }
        popupMenu.menu.add(0, ID_REMOVE, NONE, context.getString(R.string.remove))

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> {
                    actionListener.onDroidMove(droid, -1)
                }

                ID_MOVE_DOWN -> {
                    actionListener.onDroidMove(droid, 1)
                }

                ID_REMOVE -> {
                    actionListener.onDroidDelete(droid)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    override fun getItemCount() = droids.size

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
    }
}

