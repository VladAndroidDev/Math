package com.v.nevi.p.sv.android.math.screens.tabs.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.v.nevi.p.sv.android.math.databinding.FragmentHistoryBinding
import com.v.nevi.p.sv.android.math.utils.getFactoryWithRepository

class HistoryFragment : Fragment(){

    private lateinit var binding:FragmentHistoryBinding
    private val viewModel:HistoryViewModel by viewModels {
        getFactoryWithRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater,container,false).apply {
            viewmodel = viewModel
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}