package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import com.v.nevi.p.sv.android.math.model.Task

class MultiplicationOperation:Operation {

    override var task: Task? = null

    override fun generateTask(startRange:Int,endRange: Int): Task {
        val firstValue = GenerateRandomNumber.execute(startRange,endRange)
        val secondValue = GenerateRandomNumber.execute(startRange,endRange)
        val answer = firstValue * secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue)
        task = Task(answer.toLong(),stringRepresentation)
        return task as Task
    }

    override fun generateAnswers(numberAnswers:Int):List<Long> {
        val listAnswers: MutableList<Long> = mutableListOf()
        val answer = task!!.answer
        val indexCorrectAnswer=GenerateRandomNumber.execute(0,numberAnswers-1)
        listAnswers.add(answer)
        for (i in 0 until numberAnswers){
            if(i!=indexCorrectAnswer){
                listAnswers.add(i,GenerateRandomNumber.execute(0,10).toLong())
            }
        }
        return listAnswers
    }

    override fun getOperationSymbol():Char='x'
}