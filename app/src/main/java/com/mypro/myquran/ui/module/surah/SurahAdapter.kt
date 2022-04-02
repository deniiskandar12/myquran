package com.mypro.myquran.ui.module.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mypro.myquran.data.database.surah.Surah
import com.mypro.myquran.databinding.RowSurahBinding

class SurahAdapter() : ListAdapter<Surah, SurahAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Surah>() {
        override fun areItemsTheSame(oldItem: Surah, newItem: Surah): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Surah, newItem: Surah): Boolean {
            return oldItem == newItem
        }
    }

    var onClick: ((Surah) -> Unit)? = null

    inner class ViewHolder(private val binding: RowSurahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(surah: Surah) {
            binding.surah = surah
            binding.root.setOnClickListener {
                onClick?.invoke(surah)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = RowSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val surah = getItem(position)
        holder.bind(surah)
    }
}