package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import com.v.nevi.p.sv.android.math.model.Task
import kotlinx.parcelize.Parcelize

class MultiplicationOperation(private val startRange:Int, private val endRange:Int):Operation {

    override fun generateTask(): Task {
        val firstValue = GenerateRandomNumber.execute(startRange,endRange)
        val secondValue = generateNotZeroValue(startRange, endRange)
        val answer = firstValue * secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue)
        return Task(answer,stringRepresentation)
    }



    override val symbol ='x'
}