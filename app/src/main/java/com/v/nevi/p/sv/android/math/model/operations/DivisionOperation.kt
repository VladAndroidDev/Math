package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import com.v.nevi.p.sv.android.math.model.Task
import kotlinx.parcelize.Parcelize


class DivisionOperation(private val startRange:Int, private val endRange:Int):Operation {

    override fun generateTask(): Task {
        val firstValue = GenerateRandomNumber.execute(startRange,endRange)
        var secondValue:Int
        do{
            secondValue = GenerateRandomNumber.execute(startRange,endRange)
        }while (secondValue==0||firstValue % secondValue!=0)
        val answer = firstValue/secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue)
        return Task(answer.toLong(),stringRepresentation)
    }

    override val symbol ='÷'
}