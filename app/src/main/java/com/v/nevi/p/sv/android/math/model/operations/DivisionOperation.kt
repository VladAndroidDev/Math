package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import kotlin.math.abs

private const val MIN_DIFFERENCE_IN_RANGE = 5

class DivisionOperation(private val startRange: Int, private val endRange: Int) : Operation {

    private val condition: Boolean

    init {
        val firstValue = if (startRange == 0) {
            1
        } else {
            startRange
        }
        val secondValue = if (endRange == 0) {
            1
        } else {
            endRange
        }
        val resultDivision: Int = secondValue / firstValue
        condition = abs(endRange - startRange) > MIN_DIFFERENCE_IN_RANGE && resultDivision > 2
    }

    override fun generateTask(): Task {
        val firstValue: Int
        var secondValue: Int
        if (condition) {
            val divider = selectOr(2, 3, 5)
            firstValue = generateValueWithCondition(startRange, endRange) {
                it > endRange / 2 && abs(it) % divider == 0 && abs(it) / divider >= startRange
            }
            secondValue = generateValueWithCondition(
                startRange,
                endRange
            ) {
                it != 0 && firstValue % it == 0 && it != firstValue && it != 1
            }
        } else {
            firstValue = GenerateRandomNumber.execute(startRange, endRange)
            secondValue = generateValueWithCondition(startRange, endRange) {
                it != 0 && firstValue % it == 0
            }
        }
        val answer = firstValue / secondValue
        val stringRepresentation = getStringRepresentation(firstValue, secondValue)
        return Task(answer, stringRepresentation)
    }

    override fun generateAnswers(numberAnswers: Int, correctAnswer: Int): List<Int> {
        val listAnswers: MutableList<Int> = mutableListOf()
        listAnswers.apply {
            add(correctAnswer)
            when (numberAnswers) {
                4 -> {
                    addOr(correctAnswer - 1, correctAnswer + 1)
                    addOr(correctAnswer - 2, correctAnswer + 2)
                    addOr(correctAnswer - 3, correctAnswer + 3)
                }
                6 -> {
                    addOr(correctAnswer - 1, correctAnswer + 1)
                    addOr(correctAnswer - 2, correctAnswer + 2)
                    addOr(correctAnswer - 3, correctAnswer + 3)
                    addOr(correctAnswer - 4, correctAnswer + 4)
                    addOr(correctAnswer - 5, correctAnswer + 5)
                }
                9 -> {
                    addOr(correctAnswer - 1, correctAnswer + 1)
                    addOr(correctAnswer - 2, correctAnswer + 2)
                    addOr(correctAnswer - 3, correctAnswer + 3)
                    addOr(correctAnswer - 4, correctAnswer + 4)
                    addOr(correctAnswer - 5, correctAnswer + 5)
                    addOr(correctAnswer - 6, correctAnswer + 6)
                    addOr(correctAnswer - 7, correctAnswer + 7)
                    addOr(correctAnswer - 8, correctAnswer + 8)
                }
                12 -> {
                    addOr(correctAnswer - 1, correctAnswer + 1)
                    addOr(correctAnswer - 2, correctAnswer + 2)
                    addOr(correctAnswer - 3, correctAnswer + 3)
                    addOr(correctAnswer - 4, correctAnswer + 4)
                    addOr(correctAnswer - 5, correctAnswer + 5)
                    addOr(correctAnswer - 6, correctAnswer + 6)
                    addOr(correctAnswer - 7, correctAnswer + 7)
                    addOr(correctAnswer - 8, correctAnswer + 8)
                    addOr(correctAnswer - 9, correctAnswer + 9)
                    addOr(correctAnswer - 10, correctAnswer + 10)
                    addOr(correctAnswer - 11, correctAnswer + 11)
                }
            }
        }
        return listAnswers.apply {
            shuffle()
        }
    }

    override val symbol = '÷'
}