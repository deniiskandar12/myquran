package com.mypro.myquran.ui.module.detail_surah

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.mypro.myquran.data.database.detail_surah.DetailSurah
import com.mypro.myquran.data.database.surah.Surah
import com.mypro.myquran.data.network.ApiStatus
import com.mypro.myquran.ui.module.surah.SurahAdapter

@BindingAdapter("listData", "showData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<DetailSurah>?,
    showData: () -> Unit
) {
    val adapter = recyclerView.adapter as DetailSurahAdapter
    adapter.submitList(data)
    if (data?.isNotEmpty() == true) {
        showData()
    }
}

@BindingAdapter("status")
fun bindStatus(
    recyclerView: RecyclerView,
    status: ApiStatus?
) {
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
fun bindStatus(
    shimmer: ShimmerFrameLayout,
    status: ApiStatus?
) {
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