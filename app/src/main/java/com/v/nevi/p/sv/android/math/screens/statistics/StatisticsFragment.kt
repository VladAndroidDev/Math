package com.v.nevi.p.sv.android.math.screens.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentStatisticBinding
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.HistoryPlayViewModelFactory
import com.v.nevi.p.sv.android.math.utils.findTopNavController
import com.v.nevi.p.sv.android.math.utils.getFactoryWithPlayHistory

class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticBinding
    private val viewModel: StatisticsViewModel by viewModels {
        getFactoryWithPlayHistory(arguments?.getParcelable(ARG_HISTORY_PLAY)!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            navigateToNextAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
    }

    private fun setObservers(){
        viewModel.onExitEvent.observe(viewLifecycleOwner,EventObserver{
            navigateToNextAction()
        })
    }

    private fun navigateToNextAction(){
        findNavController().navigate(R.id.next_action)
    }

    companion object{
        const val ARG_HISTORY_PLAY="arg-history-play"
    }
}