package com.v.nevi.p.sv.android.math.screens.playsettings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentPlaySettingsBinding
import com.v.nevi.p.sv.android.math.screens.playsettings.viewpager.PlaySettingsFragmentStateAdapter
import com.v.nevi.p.sv.android.math.utils.EventObserver

class PlaySettingsFragment : Fragment() {

    private lateinit var binding: FragmentPlaySettingsBinding
    private val viewModel: PlaySettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaySettingsBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        binding.apply {
            viewPager.adapter = PlaySettingsFragmentStateAdapter(requireActivity())
            TabLayoutMediator(tabLayout,viewPager){ tab,position ->
                val arrayStrings = context?.resources?.getStringArray(R.array.tabs)
                tab.text = arrayStrings?.get(position)
            }.attach()
        }
    }

    private fun setObservers() {
        viewModel.onStartClickEvent.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(R.id.action_play_settings_dest_to_play_dest)
        })
        viewModel.showToastEvent.observe(viewLifecycleOwner,EventObserver{
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
    }
}