package com.mypro.myquran.ui.module.detail_surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mypro.myquran.data.database.detail_surah.DetailSurah
import com.mypro.myquran.data.database.surah.Surah
import com.mypro.myquran.databinding.RowDetailSurahBinding
import com.mypro.myquran.databinding.RowSurahBinding
import com.mypro.myquran.ui.module.surah.SurahAdapter

class DetailSurahAdapter() : ListAdapter<DetailSurah, DetailSurahAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<DetailSurah>() {
        override fun areItemsTheSame(oldItem: DetailSurah, newItem: DetailSurah): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DetailSurah, newItem: DetailSurah): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: RowDetailSurahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailSurah: DetailSurah) {
            binding.detailSurah = detailSurah
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = RowDetailSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detailSurah = getItem(position)
        holder.bind(detailSurah)
    }
}