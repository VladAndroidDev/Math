package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber

class AdditionOperation(private val startRange: Int, private val endRange: Int) : Operation {


    override fun generateTask(): Task {
        val firstValue = generateNotZeroValue(startRange, endRange)
        val secondValue = GenerateRandomNumber.execute(startRange,endRange)
        val answer = firstValue + secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue)
        return Task(answer, stringRepresentation)
    }

    override val symbol = '+'

}