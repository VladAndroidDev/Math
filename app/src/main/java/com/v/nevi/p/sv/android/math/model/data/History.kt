package com.v.nevi.p.sv.android.math.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.v.nevi.p.sv.android.math.utils.createStringTimeRepresentation
import com.v.nevi.p.sv.android.math.utils.getSupportedLocale
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "history")
data class History(@PrimaryKey val date: Date, var numberTasks:Int, var numberCorrectAnswers:Int, var time:Long){

    val timeToString:String
    get() {
        return createStringTimeRepresentation(time)
    }

    val averageTime:String
    get() {
        val averageTime = time/numberTasks
        return createStringTimeRepresentation(averageTime)
    }

    val quality:String
    get() {
        return (numberCorrectAnswers.toDouble()/numberTasks*100).toInt().toString()+"%"
    }

    val dateToString:String
    get() {
        val dateFormat = SimpleDateFormat("EEEE, d MMMM", getSupportedLocale())
        return dateFormat.format(date)
    }

    val numberTasksToString:String
    get() {
        return numberTasks.toString()
    }

    val numberCorrectAnswersToString:String
    get() {
        return numberCorrectAnswers.toString()
    }

    fun update(historyPlay: History){
        numberTasks+=historyPlay.numberTasks
        numberCorrectAnswers+=historyPlay.numberCorrectAnswers
        time+=historyPlay.time
    }
}