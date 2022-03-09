package com.v.nevi.p.sv.android.math.screens.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentPlayBinding
import com.v.nevi.p.sv.android.math.screens.play.adapters.HistoryAdapter
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.findTopNavController

class PlayFragment:Fragment() {

    private lateinit var binding:FragmentPlayBinding
    private val viewModel:PlayViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayBinding.inflate(inflater,container,false).apply {
            this.viewmodel = viewModel
            recyclerViewHistory.adapter = HistoryAdapter(viewModel)
        }
        binding.lifecycleOwner=viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        viewModel.startPlay()
    }

    private fun setObservers(){
        viewModel.onFinishGameEvent.observe(viewLifecycleOwner,EventObserver{
            navigateToNextDest()
        })
    }

    private fun navigateToNextDest(){
        findTopNavController().navigate(R.id.statistics_dest)
    }
}