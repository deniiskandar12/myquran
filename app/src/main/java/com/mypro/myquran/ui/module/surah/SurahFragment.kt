package com.mypro.myquran.ui.module.surah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.mypro.myquran.MainApplication
import com.mypro.myquran.R
import com.mypro.myquran.data.LastReadDataStore
import com.mypro.myquran.databinding.FragmentSurahBinding

class SurahFragment : Fragment() {
    private lateinit var binding: FragmentSurahBinding
    private val viewModel: SurahViewModel by viewModels {
        SurahViewModelFactory(
            (activity?.application as MainApplication)
        )
    }
    private val adapter by lazy { SurahAdapter() }
    private lateinit var lastReadDataStore: LastReadDataStore

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
        adapter.onClick = { surah ->
            val action = SurahFragmentDirections.actionSurahFragmentToDetailSurahFragment(surah)
            findNavController().navigate(action)
        }
        lastReadDataStore = LastReadDataStore(requireContext())
        lastReadDataStore.lastReadFlow.asLiveData().observe(viewLifecycleOwner) { value ->
            if(value.isEmpty()){
                hideLastRead()
            }else{
                showLastRead(value)
            }
        }
    }

    private fun hideLastRead(){
        binding.vBlueBg.visibility = View.GONE
        binding.imgLastRead.visibility = View.GONE
        binding.tvLastRead.visibility = View.GONE
        binding.tvLastReadLabel.visibility = View.GONE
    }

    private fun showLastRead(lastRead: String){
        binding.vBlueBg.visibility = View.VISIBLE
        binding.imgLastRead.visibility = View.VISIBLE
        binding.tvLastRead.visibility = View.VISIBLE
        binding.tvLastReadLabel.visibility = View.VISIBLE
        binding.tvLastRead.text = lastRead
    }
}