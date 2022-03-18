package com.v.nevi.p.sv.android.math.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.v.nevi.p.sv.android.math.model.HistoryPlay
import com.v.nevi.p.sv.android.math.screens.statistics.StatisticsViewModel

class HistoryPlayViewModelFactory(private val historyPlay: HistoryPlay):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatisticsViewModel(historyPlay) as T
    }
}