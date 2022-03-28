package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.generator.GenerateRandomNumber
import java.io.Serializable


interface Operation : Serializable {

    val symbol: Char

    fun generateTask(): Task

    fun generateAnswers(numberAnswers: Int, correctAnswer: Int): List<Int> {
        val listAnswers: MutableList<Int> = mutableListOf()
        listAnswers.add(correctAnswer)

        if (correctAnswer in -99..99) {
            addAnswersInRangeUpToOneHundred(correctAnswer,numberAnswers, listAnswers)
        } else if (correctAnswer in -999..-100||correctAnswer in 100..999) {
            addAnswersInRangeLessThousand(correctAnswer,numberAnswers,listAnswers)
        } else if (correctAnswer in 1000..9999 || correctAnswer in -9999..-1000) {
            addAnswersInRangeOverThousand(correctAnswer,numberAnswers,listAnswers)
        }else if(correctAnswer>9999||correctAnswer<-9999){
            addAnswersInRangeOver9999(correctAnswer,numberAnswers,listAnswers)
        }

        val startRange: Int
        val endRange: Int
        if (correctAnswer > 0 && correctAnswer - 10 < 0) {
            startRange = 0
            endRange = numberAnswers + 2
        } else if (correctAnswer < 0 && correctAnswer + 10 > 0) {
            endRange = 0
            startRange = -numberAnswers - 2
        } else {
            val max = listAnswers.maxOrNull()
            val min = listAnswers.minOrNull()
            startRange = min!!+1
            endRange=max!!-1
        }

        createRandomAnswers(numberAnswers, startRange, endRange, listAnswers)

        return listAnswers.apply {
            shuffle()
        }
    }

    private fun addAnswersInRangeUpToOneHundred(
        answer: Int,
        numberAnswers: Int,
        listAnswers: MutableList<Int>
    ) {
        listAnswers.addOr(answer - 10, answer + 10)
        listAnswers.addOr(answer - 20, answer + 20)
    }

    private fun addAnswersInRangeLessThousand(answer:Int,
                                              numberAnswers: Int,
                                              listAnswers: MutableList<Int>){
        when(numberAnswers){
            4->{
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-20,answer+20)
            }
            6->{
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-20,answer+20)
            }
            else->
            {
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-20,answer+20)
                listAnswers.addOr(answer-30,answer+30)
            }
        }
    }
    private fun addAnswersInRangeOverThousand(answer:Int,
                                              numberAnswers: Int,
                                              listAnswers: MutableList<Int>){
        when(numberAnswers){
            4->{
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-50,answer+50)
                listAnswers.addOr(answer-100,answer+100)
            }
            6->{
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-50,answer+50)
                listAnswers.addOr(answer-100,answer+100)
            }
            else->
            {
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-50,answer+50)
                listAnswers.addOr(answer-100,answer+100)
                listAnswers.addOr(answer-110,answer+110)
                listAnswers.addOr(answer-120,answer+120)
            }
        }
    }
    private fun addAnswersInRangeOver9999(answer: Int,
                                              numberAnswers: Int,
                                              listAnswers: MutableList<Int>){
        when(numberAnswers){
            4->{
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-100,answer+100)
                listAnswers.addOr(answer-1000,answer+1000)
            }
            6->{
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-100,answer+100)
                listAnswers.addOr(answer-100,answer+100)
                listAnswers.addOr(answer-1000,answer+1000)
            }
            else->
            {
                listAnswers.addOr(answer-10,answer+10)
                listAnswers.addOr(answer-100,answer+100)
                listAnswers.addOr(answer-100,answer+100)
                listAnswers.addOr(answer-1000,answer+1000)
                listAnswers.addOr(answer-1000,answer+1000)
            }
        }
    }

    private fun createRandomAnswers(
        numberAnswers: Int,
        startRange:Int,
        endRange: Int,
        listAnswers: MutableList<Int>
    ) {

        while (listAnswers.size < numberAnswers) {
            val value = generateValueWithCondition(startRange, endRange) { value ->
                !listAnswers.contains(value)
            }
            listAnswers.add(value)
        }
    }

    fun generateValueWithCondition(
        startRange: Int,
        endRange: Int,
        condition: (Int) -> Boolean
    ): Int {
        var value: Int
        do {
            value = GenerateRandomNumber.execute(startRange, endRange)
        } while (!condition.invoke(value))
        return value
    }

    fun generateNotZeroValue(startRange: Int, endRange: Int): Int {
        return generateValueWithCondition(
            startRange, endRange
        ) {
            it != 0
        }
    }

    fun getStringRepresentation(firstValue: Int, secondValue: Int): String {
        return String.format("$firstValue $symbol $secondValue = ")
    }
}

fun <T> MutableList<T>.addOr(firstValue: T, secondValue: T) {
    val randomNumber = GenerateRandomNumber.execute(0, 1)
    if (randomNumber == 0) {
        this.add(firstValue)
    } else {
        this.add(secondValue)
    }
}