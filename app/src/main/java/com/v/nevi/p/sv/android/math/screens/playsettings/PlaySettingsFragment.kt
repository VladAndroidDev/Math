package com.v.nevi.p.sv.android.math.screens.playsettings


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentPlaySettingsBinding
import com.v.nevi.p.sv.android.math.screens.playsettings.range.EnterValueDialogFragment
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.getResult

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
        getResult(KEY_NEW_VALUE)?.observe(viewLifecycleOwner){
            viewModel.setResult(it)
            binding.invalidateAll()
        }
    }

    private fun setObservers() {
        viewModel.onEnterFirstValueClickEvent.observe(viewLifecycleOwner, EventObserver {
            onEnterValueClick(it)
        })
        viewModel.onEnterSecondValueClickEvent.observe(viewLifecycleOwner, EventObserver {
            onEnterValueClick(it)
        })
        viewModel.showToastEvent.observe(viewLifecycleOwner,EventObserver{
            Toast.makeText(requireContext(),getString(it),Toast.LENGTH_LONG).show()
        })
        viewModel.onStartClickEvent.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(R.id.play_dest)
            Log.d("MyTag", viewModel.playSettings.toString())
        })
    }

    private fun onEnterValueClick(currentValue: Int) {
        findNavController().navigate(
            R.id.action_play_settings_dest_to_enter_value_dest, bundleOf(
                EnterValueDialogFragment.KEY_CURRENT_VALUE to currentValue
            ),
            null
        )
    }

    companion object {
        const val KEY_NEW_VALUE = "new-value"
    }
}