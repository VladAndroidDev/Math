package com.v.nevi.p.sv.android.math.screens.tabs.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import com.v.nevi.p.sv.android.math.model.data.History
import com.v.nevi.p.sv.android.math.model.data.Result
import com.v.nevi.p.sv.android.math.model.data.Result.Success
import com.v.nevi.p.sv.android.math.model.data.source.HistoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository):ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    val historyList = repository.observeHistories().distinctUntilChanged().switchMap {
        val result = MutableLiveData<List<History>>()
        if(it is Success){
            result.value = it.data
        }else{
            result.value = emptyList()
        }
        result
    }

    fun deleteAllHistories(){
        coroutineScope.launch {
            repository.deleteAllHistories()
        }
    }

}