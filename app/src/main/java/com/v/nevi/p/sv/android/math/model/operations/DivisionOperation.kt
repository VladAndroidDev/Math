package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import com.v.nevi.p.sv.android.math.model.Task

class DivisionOperation:Operation {

    override var task: Task? = null

    override fun generateTask(startRange:Int,endRange: Int): Task {
        val answer = GenerateRandomNumber.execute(startRange,endRange)
        val secondValue = GenerateRandomNumber.execute(startRange,endRange)
        val firstValue = answer * secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue, answer)
        task = Task(answer.toLong(),stringRepresentation)
        return task as Task
    }

    override fun generateAnswers(numberAnswers:Int):List<Long> {
        TODO("Not yet implemented")
    }

    override fun getOperationSymbol():Char='÷'
}