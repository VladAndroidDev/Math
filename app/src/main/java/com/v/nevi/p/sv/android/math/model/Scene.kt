package com.v.nevi.p.sv.android.math.model

import com.v.nevi.p.sv.android.math.model.generator.Generator
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

class Scene{

    private val playSettings: PlaySettings = PlaySettings.getInstance()
    var historyPlay = HistoryPlay()

    val numberAnswers
        get() = playSettings.numberAnswers

    val gameDuration
        get() = playSettings.gameDuration

    val numberCorrectAnswers
        get() = historyPlay.numberCorrectAnswers

    val numberInCorrectAnswers
        get() = historyPlay.numberInCorrectAnswers

    private var generator = Generator(playSettings)

    fun createItemPlayHistory(answer: Long): StatisticsItem.ItemPlayHistory =
        historyPlay.createItemPlayHistory(generator.getLastTask(), answer)

    fun getAnswers() = generator.getAnswers()

    fun getTask() = generator.generateTask()

    fun isTasksOver() = playSettings.numberTasks == historyPlay.numberResolvedTasks

    fun isTimeOver() = playSettings.gameDuration.toLong() == historyPlay.timePlay

    fun updateTime() {
        historyPlay.updateTime()
    }

    fun getHistoryPlay() = historyPlay.createEntityForDb()

    fun isEmptyHistoryPlay(): Boolean  = historyPlay.isEmpty()

    val timePlay:Long
        get() {
            return historyPlay.timePlay
        }
}