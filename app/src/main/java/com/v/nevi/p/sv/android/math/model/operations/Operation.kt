package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import java.io.Serializable


interface Operation:Serializable{

    val symbol:Char

    fun generateTask(): Task

    fun generateAnswers(numberAnswers:Int,correctAnswer:Long):List<Long> {
        val listAnswers: MutableList<Long> = mutableListOf()
        listAnswers.add(correctAnswer)
        val startRange:Int
        val endRange:Int
        if(correctAnswer>0 && correctAnswer-10<0){
            startRange=0
            endRange=numberAnswers+4
        }else if(correctAnswer<0 && correctAnswer+10>0){
            endRange=0
            startRange = -numberAnswers-4
        }else{
            startRange = (correctAnswer-10).toInt()
            endRange = (correctAnswer+10).toInt()
        }
        for (i in 0 until numberAnswers-1){
            var value:Long
            do {
                value = GenerateRandomNumber.execute(startRange,endRange).toLong()
            }while(listAnswers.contains(value))
            listAnswers.add(i,value)
        }
        return listAnswers.apply {
            shuffle()
        }
    }

    fun getStringRepresentation(firstValue:Int,secondValue:Int):String{
        return String.format("$firstValue $symbol $secondValue = ")
    }
}