package com.mypro.myquran.ui.module.surah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mypro.myquran.MainApplication
import com.mypro.myquran.R
import com.mypro.myquran.databinding.FragmentSurahBinding

class SurahFragment : Fragment() {
    private lateinit var binding: FragmentSurahBinding
    private val viewModel: SurahViewModel by viewModels {
        SurahViewModelFactory(
            (activity?.application as MainApplication)
        )
    }
    private val adapter by lazy { SurahAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_surah, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@SurahFragment.viewLifecycleOwner
            viewModel = this@SurahFragment.viewModel
            rvSurah.adapter = adapter
        }
    }
}