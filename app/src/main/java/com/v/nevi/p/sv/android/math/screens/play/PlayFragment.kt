package com.v.nevi.p.sv.android.math.screens.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.v.nevi.p.sv.android.math.databinding.FragmentPlayBinding

class PlayFragment:Fragment() {

    private lateinit var binding:FragmentPlayBinding
    private val viewModel:PlayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayBinding.inflate(inflater,container,false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }
}