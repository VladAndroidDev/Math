package com.v.nevi.p.sv.android.math.screens.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentStatisticBinding
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
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 16908332) {
            findTopNavController().popBackStack(R.id.tabs_dest, false)
            return true
        }
        return super.onOptionsItemSelected(item)
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

    companion object{
        const val ARG_HISTORY_PLAY="arg-history-play"
    }
}