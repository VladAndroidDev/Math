package com.v.nevi.p.sv.android.math.screens.play

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentPlayBinding
import com.v.nevi.p.sv.android.math.screens.play.adapters.HistoryAdapter
import com.v.nevi.p.sv.android.math.screens.statistics.StatisticsFragment
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.findTopNavController
import com.v.nevi.p.sv.android.math.utils.getFactoryWithRepository
import com.v.nevi.p.sv.android.math.utils.getResult

enum class EventMenu{
    CONTINUE,
    TOTALS,
    EXIT
}

class PlayFragment : Fragment() {

    companion object{
        const val KEY_EVENT_MENU="event-menu"
    }

    private lateinit var binding: FragmentPlayBinding
    val viewModel: PlayViewModel by viewModels {
        getFactoryWithRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 16908332) {
            exitPlay()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
            recyclerViewHistory.adapter = HistoryAdapter(viewModel)
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        getResult(KEY_EVENT_MENU)?.observe(viewLifecycleOwner){
            viewModel.setResult(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startPlay()
    }

    private fun setObservers() {
        viewModel.onFinishPlayEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToNextDest(it)
        })
        viewModel.onPausePlayEvent.observe(viewLifecycleOwner){
            openPauseDialog()
        }
        viewModel.onExitEvent.observe(viewLifecycleOwner){
            exitPlay()
        }
    }

    private fun exitPlay(){
        findTopNavController().popBackStack(R.id.tabs_dest, false)
    }

    private fun openPauseDialog() {
        findTopNavController().navigate(R.id.pause_dialog_dest)
    }

    private fun navigateToNextDest(arg: Parcelable) {
        findTopNavController().navigate(
            R.id.statistics_dest,
            bundleOf(StatisticsFragment.ARG_HISTORY_PLAY to arg)
        )
    }
}