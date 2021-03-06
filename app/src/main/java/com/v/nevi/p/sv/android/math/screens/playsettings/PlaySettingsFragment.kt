package com.v.nevi.p.sv.android.math.screens.playsettings


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            val inflater = LayoutInflater.from(view.context)
            TabLayoutMediator(tabLayout,viewPager){ tab,position ->
                val arrayStrings = context?.resources?.getStringArray(R.array.tabs)
                val customView  = inflater.inflate(R.layout.custom_tab_view,tabLayout,false) as TextView
                customView.text = arrayStrings?.get(position)
                tab.customView = customView
            }.attach()
        }
    }

    private fun setObservers() {
        viewModel.onStartClickEvent.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(R.id.action_play_settings_dest_to_play_dest)
        })
        viewModel.showToastEvent.observe(viewLifecycleOwner,EventObserver{
            val toast = Toast.makeText(requireContext(),it,Toast.LENGTH_LONG)
            val textView = toast.view!!.findViewById<TextView>(android.R.id.message)
            textView.gravity = Gravity.CENTER
            toast.show()
        })
    }
}