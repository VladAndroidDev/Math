package com.v.nevi.p.sv.android.math.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.v.nevi.p.sv.android.math.utils.createStringTimeRepresentation
import com.v.nevi.p.sv.android.math.utils.getSupportedLocale
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "history")
data class History(
    @PrimaryKey val date: Date,
    var numberTasks: Int,
    var numberCorrectAnswers: Int,
    var time: Long
) {

    val timeToString: String
        get() {
            return createStringTimeRepresentation(time)
        }

    val averageTime: String
        get() {
            val averageTime = time / numberTasks
            return createStringTimeRepresentation(averageTime)
        }

    val quality: String
        get() {
            return (numberCorrectAnswers.toDouble() / numberTasks * 100).toInt().toString() + "%"
        }

    val dateToString: String
        get() {
            val dateFormat = SimpleDateFormat("EEEE, d MMMM", getSupportedLocale())
            val dateToString = dateFormat.format(date)
            val res = dateToString.substring(0,1).uppercase()+dateToString.substring(1,dateToString.length)
//            val res = StringBuilder()
//            res.append(words[0].substring(0, 1).uppercase())
//                .append(words[0].substring(1, words[0].length))
//                .append(" ")
//                .append(words[1])
////                .append(" ")
//                .append(words[2].substring(0,1).uppercase())
//                .append(words[2].substring(1,words[2].length))
            return res
        }

    val numberTasksToString: String
        get() {
            return numberTasks.toString()
        }

    val numberCorrectAnswersToString: String
        get() {
            return numberCorrectAnswers.toString()
        }

    fun update(historyPlay: History) {
        numberTasks += historyPlay.numberTasks
        numberCorrectAnswers += historyPlay.numberCorrectAnswers
        time += historyPlay.time
    }
}