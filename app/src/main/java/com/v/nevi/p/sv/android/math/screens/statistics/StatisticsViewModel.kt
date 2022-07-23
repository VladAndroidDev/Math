package com.v.nevi.p.sv.android.math.screens.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.model.HistoryPlay
import com.v.nevi.p.sv.android.math.model.StatisticsItem
import com.v.nevi.p.sv.android.math.utils.Event
import com.v.nevi.p.sv.android.math.utils.createStringTimeRepresentation

class StatisticsViewModel(private val historyPlay: HistoryPlay):ViewModel() {

    init {
        Log.d("MyTimePlay",historyPlay.timePlay.toString())
    }
    val totalTasks:String
    get() = historyPlay.numberResolvedTasks.toString()

    val correctAnswers:String
    get() = historyPlay.numberCorrectAnswers.toString()

    val time:String
    get() {
        return createStringTimeRepresentation(historyPlay.timePlay)
    }

    val averageTime:String
    get() {
        val averageTime = if(historyPlay.numberResolvedTasks!=0){
            historyPlay.timePlay / historyPlay.numberResolvedTasks
        }else{
            0
        }
        return createStringTimeRepresentation(averageTime)
        }

    val quality:String
    get() = "${historyPlay.quality}%"

    val listStatisticsItems:List<StatisticsItem>
    get() = historyPlay.listStatistics

    private val _onExitEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onExitEvent:LiveData<Event<Unit>> = _onExitEvent

    fun onExit(){
        _onExitEvent.value = Event(Unit)
    }

}