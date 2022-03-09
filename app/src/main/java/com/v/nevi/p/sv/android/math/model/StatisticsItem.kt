package com.v.nevi.p.sv.android.math.model

class StatisticsItem(val isAnswerCorrect:Boolean, val answer:String, val correctAnswer:String) {
    fun createPlayHistory() = ItemPlayHistory(isAnswerCorrect,answer)

    class ItemPlayHistory(
        val isAnswerCorrect: Boolean,
        val answer: String
    )
}
