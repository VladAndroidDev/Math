package com.v.nevi.p.sv.android.math.model

import com.v.nevi.p.sv.android.math.model.generator.Generator
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

class Scene {

    private val playSettings: PlaySettings = PlaySettings.getInstance()

    val numberAnswers
        get() = playSettings.numberAnswers

    val gameDuration
        get() = playSettings.gameDuration

    val numberCorrectAnswers
        get() = HistoryPlay.numberCorrectAnswers

    val numberInCorrectAnswers
        get() = HistoryPlay.numberInCorrectAnswers

    private val generator = Generator(playSettings)

    fun createItemPlayHistory(answer: Long): StatisticsItem.ItemPlayHistory =
        HistoryPlay.createItemPlayHistory(generator.getLastTask(), answer)

    fun getAnswers() = generator.getAnswers()

    fun getTask() = generator.generateTask()

    fun isTasksOver() = playSettings.numberTasks == HistoryPlay.numberResolvedAnswers

    fun isTimeOver() = playSettings.gameDuration == HistoryPlay.gameDuration

    fun updateTime(time: Int) {
        HistoryPlay.updateTime(time)
    }
}