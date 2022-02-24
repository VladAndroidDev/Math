package com.v.nevi.p.sv.android.math.screens.tabs.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentStartBinding
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.findTopNavController

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val viewModel: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.startPlaySettingsEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToPlaySettings()
        })
    }

    private fun navigateToPlaySettings() {
        findTopNavController().navigate(R.id.action_tabsFragment_to_playSettingsFragment)
    }
}