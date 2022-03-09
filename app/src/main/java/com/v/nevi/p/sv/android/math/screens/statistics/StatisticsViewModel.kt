package com.v.nevi.p.sv.android.math.screens.statistics

import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.model.HistoryPlay
import com.v.nevi.p.sv.android.math.model.StatisticsItem

class StatisticsViewModel:ViewModel() {

    val totalTasks:String
    get() = HistoryPlay.numberResolvedAnswers.toString()

    val correctAnswers:String
    get() = HistoryPlay.numberCorrectAnswers.toString()

    val time:String
    get() {
        val totalSeconds = HistoryPlay.gameDuration
        val minutes = totalSeconds/60
        val seconds = totalSeconds%60
        return createStringTimeRepresentation(minutes, seconds)
    }

    val averageTime:String
    get() {
        val averageTime = HistoryPlay.gameDuration/HistoryPlay.numberResolvedAnswers
        val minutes = averageTime/60
        val seconds = averageTime%60
        return createStringTimeRepresentation(minutes, seconds)
    }

    val quality:String
    get() = "${HistoryPlay.quality}%"

    val listStatisticsItems:List<StatisticsItem>
    get() = HistoryPlay.listStatistics

    private fun createStringTimeRepresentation(minutes:Int, seconds:Int):String{
        val minutesStr:String = if(minutes<10){
            "0$minutes"
        }else{
            minutes.toString()
        }
        val secondsStr = if(seconds<10){
            "0$seconds"
        }else{
            seconds.toString()
        }
        return "$minutesStr:$secondsStr"
    }
}