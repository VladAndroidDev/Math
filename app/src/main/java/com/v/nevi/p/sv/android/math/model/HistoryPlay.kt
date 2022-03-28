package com.v.nevi.p.sv.android.math.model

import android.os.Parcelable
import android.util.Log
import com.v.nevi.p.sv.android.math.model.data.History
import com.v.nevi.p.sv.android.math.utils.getCurrentDate
import kotlinx.parcelize.Parcelize

@Parcelize
class HistoryPlay:Parcelable {

    val listStatistics:MutableList<StatisticsItem> = mutableListOf()
    var timePlay: Long = 0
    var numberResolvedTasks: Int = 0
    var numberCorrectAnswers = 0
    var numberInCorrectAnswers = 0
    var quality = 0
    get() = (numberCorrectAnswers.toDouble()/numberResolvedTasks*100).toInt()

    fun createItemPlayHistory(task: Task, answerUser: Long): StatisticsItem.ItemPlayHistory {
        val isAnswerCorrect = task.answer.toLong() == answerUser
        updateAnswers(isAnswerCorrect)
        val presentationHistory = task.toString() + answerUser
        val presentationCorrect = task.toString() + task.answer
        val item = StatisticsItem(isAnswerCorrect, presentationHistory,presentationCorrect)
        listStatistics.add(item)
        return item.createPlayHistory()
    }

    private fun updateAnswers(isAnswerCorrect:Boolean){
        numberResolvedTasks++
        if(isAnswerCorrect){
            numberCorrectAnswers++
        }else{
            numberInCorrectAnswers++
        }
    }

    fun updateTime(newTime: Long) {
        timePlay = newTime
    }

    fun createEntityForDb(): History {
        return History(getCurrentDate(), numberResolvedTasks, numberCorrectAnswers, timePlay)
    }

    fun isEmpty()=numberResolvedTasks==0
}