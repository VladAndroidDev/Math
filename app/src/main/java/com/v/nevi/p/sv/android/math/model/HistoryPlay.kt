package com.v.nevi.p.sv.android.math.model

object HistoryPlay {

    val listStatistics:MutableList<StatisticsItem> = mutableListOf()
    var gameDuration: Int = 0
    var numberResolvedAnswers: Int = 0
    var numberCorrectAnswers = 0
    var numberInCorrectAnswers = 0
    val quality:Double
    get() = numberCorrectAnswers.toDouble()/numberResolvedAnswers

    fun createItemPlayHistory(task: Task, answerUser: Long): StatisticsItem.ItemPlayHistory {
        val isAnswerCorrect = task.answer == answerUser
        updateAnswers(isAnswerCorrect)
        val presentationHistory = task.toString() + answerUser
        val presentationCorrect = task.toString() + task.answer
        val item = StatisticsItem(isAnswerCorrect, presentationHistory,presentationCorrect)
        listStatistics.add(item)
        return item.createPlayHistory()
    }

    private fun updateAnswers(isAnswerCorrect:Boolean){
        numberResolvedAnswers++
        if(isAnswerCorrect){
            numberCorrectAnswers++
        }else{
            numberInCorrectAnswers++
        }
    }

    fun updateTime(newTime: Int) {
        gameDuration = newTime
    }

}