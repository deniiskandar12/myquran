package com.mypro.myquran.ui.module.surah

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.mypro.myquran.data.database.surah.Surah
import com.mypro.myquran.data.network.ApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Surah>?
) {
    val adapter = recyclerView.adapter as SurahAdapter
    adapter.submitList(data)
}

@BindingAdapter("status")
fun bindStatus(recyclerView: RecyclerView,
               status: ApiStatus?) {
    when (status) {
        ApiStatus.SUCCESS -> {
            recyclerView.visibility = View.VISIBLE
        }
        else -> {
            recyclerView.visibility = View.GONE
        }
    }
}

@BindingAdapter("status")
fun bindStatus(shimmer: ShimmerFrameLayout,
               status: ApiStatus?) {
    when (status) {
        ApiStatus.SUCCESS -> {
            shimmer.visibility = View.GONE
            shimmer.stopShimmer()
        }
        else -> {
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
        }
    }
}