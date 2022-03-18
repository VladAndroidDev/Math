package com.v.nevi.p.sv.android.math.model

import android.os.Parcelable
import com.v.nevi.p.sv.android.math.model.data.History
import com.v.nevi.p.sv.android.math.utils.getCurrentDate
import kotlinx.parcelize.Parcelize

@Parcelize
class HistoryPlay:Parcelable {

    val listStatistics:MutableList<StatisticsItem> = mutableListOf()
    var gameDuration: Long = 0
    var numberResolvedTasks: Int = 0
    var numberCorrectAnswers = 0
    var numberInCorrectAnswers = 0
    val quality:Double
    get() = numberCorrectAnswers.toDouble()/numberResolvedTasks




    fun createItemPlayHistory(task: Task, answerUser: Long): StatisticsItem.ItemPlayHistory {
        val isAnswerCorrect = task.answer == answerUser
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
        gameDuration = newTime
    }

    fun createEntityForDb(): History {
        return History(getCurrentDate(), numberResolvedTasks, numberCorrectAnswers, gameDuration)
    }


    override fun describeContents(): Int {
        return 0
    }

    fun isEmpty()=numberResolvedTasks==0
}