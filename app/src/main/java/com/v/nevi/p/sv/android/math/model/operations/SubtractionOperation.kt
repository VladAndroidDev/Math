package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import com.v.nevi.p.sv.android.math.model.Task


class SubtractionOperation(private val startRange:Int, private val endRange:Int):Operation {

    override fun generateTask(): Task {
        val firstValue = GenerateRandomNumber.execute(startRange,endRange)
        val secondValue = generateValueWithCondition(startRange,endRange){
            it!=firstValue
        }
        val  answer = firstValue - secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue)
        return Task(answer,stringRepresentation)
    }

    override val symbol ='-'

}