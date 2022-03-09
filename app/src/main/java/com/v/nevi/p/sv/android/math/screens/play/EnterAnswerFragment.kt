package com.v.nevi.p.sv.android.math.screens.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

class EnterAnswerFragment:Fragment() {

    private val viewModel:PlayViewModel by activityViewModels()
    private lateinit var binding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = viewModel.createBinding(inflater,container)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}