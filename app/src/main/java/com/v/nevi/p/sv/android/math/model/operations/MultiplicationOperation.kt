package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import com.v.nevi.p.sv.android.math.model.Task
import kotlinx.parcelize.Parcelize
import kotlin.math.abs

private const val MIN_DIFFERENCE_IN_RANGE = 5
class MultiplicationOperation(private val startRange: Int, private val endRange: Int) : Operation {

    override fun generateTask(): Task {
        val firstValue: Int
        val secondValue: Int
        if (abs(startRange - endRange) > MIN_DIFFERENCE_IN_RANGE) {
            firstValue = generateValueWithCondition(startRange, endRange) {
                it != 0 && it != 1
            }
            secondValue = generateValueWithCondition(startRange, endRange) {
                it != 0 && it != 1
            }
        } else {
            firstValue = GenerateRandomNumber.execute(startRange, endRange)
            secondValue = GenerateRandomNumber.execute(startRange, endRange)
        }
        val answer = firstValue * secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue)
        return Task(answer, stringRepresentation)
    }

    override val symbol = 'x'
}