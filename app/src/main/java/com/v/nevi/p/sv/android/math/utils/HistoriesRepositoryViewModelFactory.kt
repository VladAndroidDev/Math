package com.v.nevi.p.sv.android.math.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.v.nevi.p.sv.android.math.model.data.source.HistoryRepository
import com.v.nevi.p.sv.android.math.screens.play.PausePlayViewModel
import com.v.nevi.p.sv.android.math.screens.play.PlayViewModel
import com.v.nevi.p.sv.android.math.screens.tabs.history.HistoryViewModel
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class HistoriesRepositoryViewModelFactory(private val historyRepository: HistoryRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass){
        when {
            isAssignableFrom(PlayViewModel::class.java)-> PlayViewModel(historyRepository)
            isAssignableFrom(HistoryViewModel::class.java) -> HistoryViewModel(historyRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel class $this")
        } as T
    }
}