package com.v.nevi.p.sv.android.math.screens.statistics

import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.model.HistoryPlay
import com.v.nevi.p.sv.android.math.model.StatisticsItem
import com.v.nevi.p.sv.android.math.utils.createStringTimeRepresentation

class StatisticsViewModel(private val historyPlay: HistoryPlay):ViewModel() {

    val totalTasks:String
    get() = historyPlay.numberResolvedTasks.toString()

    val correctAnswers:String
    get() = historyPlay.numberCorrectAnswers.toString()

    val time:String
    get() {
        return createStringTimeRepresentation(historyPlay.gameDuration)
    }

    val averageTime:String
    get() {
        val averageTime = if(historyPlay.numberResolvedTasks!=0){
            historyPlay.gameDuration / historyPlay.numberResolvedTasks
        }else{
            0
        }
        return createStringTimeRepresentation(averageTime)
        }

    val quality:String
    get() = "${historyPlay.quality}%"

    val listStatisticsItems:List<StatisticsItem>
    get() = historyPlay.listStatistics

}