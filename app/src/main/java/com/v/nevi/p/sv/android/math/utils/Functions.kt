package com.v.nevi.p.sv.android.math.utils

import java.util.*

fun createStringTimeRepresentation(time:Long):String{
    val hours = time / 3600L
    val minutes = time % 3600 / 60
    val seconds = time % 3600 % 60
    val hoursStr = if(hours!=0L){
        if(hours<10){
            "0$hours:"
        }else{
            "$hours:"
        }
    }else{
        ""
    }
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
    return "$hoursStr$minutesStr:$secondsStr"
}

fun getSupportedLocale(): Locale {
    return when (Locale.getDefault()) {
        Locale.forLanguageTag("ru-RU") -> Locale.forLanguageTag("ru-RU")
        else -> Locale.ENGLISH
    }
}

fun getCurrentDate(): Date {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return Date(year, month, day)
}