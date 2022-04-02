package com.mypro.myquran.ui.module.detail_surah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mypro.myquran.MainApplication
import com.mypro.myquran.R
import com.mypro.myquran.data.LastReadDataStore
import com.mypro.myquran.databinding.FragmentDetailSurahBinding
import com.mypro.myquran.databinding.LayoutDescriptionBinding
import com.mypro.myquran.ui.module.surah.SurahAdapter
import com.mypro.myquran.ui.module.surah.SurahViewModel
import com.mypro.myquran.ui.module.surah.SurahViewModelFactory
import kotlinx.coroutines.launch

class DetailSurahFragment : Fragment() {
    private lateinit var binding: FragmentDetailSurahBinding
    private val viewModel: DetailSurahViewModel by viewModels {
        DetailSurahViewModelFactory(
            (activity?.application as MainApplication)
        )
    }
    private val args: DetailSurahFragmentArgs by navArgs()
    private val adapter by lazy { DetailSurahAdapter() }
    private lateinit var lastReadDataStore: LastReadDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_surah, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchDetailSurahs(args.surah.id)

        binding.apply {
            surah = args.surah
            viewModel = this@DetailSurahFragment.viewModel
            lifecycleOwner = this@DetailSurahFragment.viewLifecycleOwner
            rvDetailSurah.adapter = adapter
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnInfo.setOnClickListener {
                showBottomDialog()
            }
        }

        lastReadDataStore = LastReadDataStore(requireContext())
        lifecycleScope.launch{
            lastReadDataStore.saveLastRead(args.surah.name, requireContext())
        }
    }

    private fun showBottomDialog() {
        val dialogBinding = LayoutDescriptionBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
        bottomSheetDialog.setContentView(dialogBinding.root)

        with(dialogBinding) {
            val description =
                HtmlCompat.fromHtml(args.surah.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()
            tvDescription.text = description
        }

        bottomSheetDialog.show()
    }

}