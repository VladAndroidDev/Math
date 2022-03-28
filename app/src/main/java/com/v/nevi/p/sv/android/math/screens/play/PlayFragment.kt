package com.v.nevi.p.sv.android.math.screens.play

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentPlayBinding
import com.v.nevi.p.sv.android.math.screens.play.adapters.AnswerOptionsAdapter
import com.v.nevi.p.sv.android.math.screens.play.adapters.HistoryAdapter
import com.v.nevi.p.sv.android.math.screens.statistics.StatisticsFragment
import com.v.nevi.p.sv.android.math.utils.EventObserver
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
        const val KEY_EXIT_DIALOG_ARG="exit-dialog-arg"
    }

    private lateinit var binding: FragmentPlayBinding
    val viewModel: PlayViewModel by viewModels {
        getFactoryWithRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            viewModel.backPressed{
                findNavController().popBackStack()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 16908332) {
            requireActivity().onBackPressed()
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
            recyclerViewOptions.adapter = AnswerOptionsAdapter(viewModel)
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adRequestAdView = AdRequest.Builder().build()
        binding.adView.loadAd(adRequestAdView)
        setObservers()
        getResult<EventMenu>(KEY_EVENT_MENU) {
            viewModel.setResult(it)
        }
        getResult<Boolean>(KEY_EXIT_DIALOG_ARG){
            viewModel.setResult(it)
        }
        viewModel.startPlay()
    }

    override fun onResume() {
        super.onResume()
        viewModel.continuePlay()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pausePlay()
    }

    private fun setObservers() {
        viewModel.onFinishPlayEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToNextDest(it)
        })
        viewModel.onPausePlayEvent.observe(viewLifecycleOwner, EventObserver{
            openPauseDialog(it)
        })
        viewModel.onExitEvent.observe(viewLifecycleOwner, EventObserver{
            exitPlay()
        })
        viewModel.onBackPressedEvent.observe(viewLifecycleOwner, EventObserver{
            openExitDialog()
        })
    }

    private fun openExitDialog(){
        findNavController().navigate(R.id.exit_dialog_dest)
    }

    private fun exitPlay(){
        findNavController().popBackStack(R.id.tabs_dest, false)
    }

    private fun openPauseDialog(isEmptyHistoryPlay:Boolean) {
        findNavController().navigate(R.id.action_play_dest_to_pause_dialog_dest, bundleOf(PausePlayDialogFragment.KEY_ARG_IS_EMPTY_HISTORY to isEmptyHistoryPlay))
    }

    private fun navigateToNextDest(arg: Parcelable) {
        findNavController().navigate(
            R.id.statistics_dest,
            bundleOf(StatisticsFragment.ARG_HISTORY_PLAY to arg)
        )
    }

    override fun onDestroy() {
        viewModel.saveResult()
        super.onDestroy()
    }
}