package com.v.nevi.p.sv.android.math.screens.playsettings.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.ItemViewPagerPlaySettingsBinding
import com.v.nevi.p.sv.android.math.screens.playsettings.range.EnterValueDialogFragment
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.getResult

const val ARG_POSITION="arg-position"
class PagerFragment:Fragment() {

    private val viewModel:PagerViewModel by viewModels {
        PagerViewModelFactory(requireArguments().getInt(ARG_POSITION))
    }
    private lateinit var binding: ItemViewPagerPlaySettingsBinding

    companion object{

        fun NewInstance(arg:Int):Fragment {
            return PagerFragment().apply {
                arguments = bundleOf(ARG_POSITION to arg)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemViewPagerPlaySettingsBinding.inflate(inflater,container,false).apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        getResult<Int>(arguments?.getInt(ARG_POSITION).toString()){
            viewModel.setResult(it)
            binding.invalidateAll()
        }
    }

    private fun setObservers(){
        viewModel.onEnterValueClickEvent.observe(viewLifecycleOwner, EventObserver {
            onEnterValueClick(it)
        })
        viewModel.showToastEvent.observe(viewLifecycleOwner,EventObserver{
            Toast.makeText(requireContext(),getString(it), Toast.LENGTH_LONG).show()
        })
    }

    private fun onEnterValueClick(arg:Int) {
        findNavController().navigate(
            R.id.action_play_settings_dest_to_enter_value_dest, bundleOf(EnterValueDialogFragment.KEY_POSITION to arg)
        )
    }
}
class PagerViewModelFactory(private val arg:Int):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PagerViewModel(arg) as T
    }
}