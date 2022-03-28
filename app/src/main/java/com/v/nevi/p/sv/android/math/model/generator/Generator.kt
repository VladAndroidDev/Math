package com.v.nevi.p.sv.android.math.model.generator


import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

class Generator(private val playSettings: PlaySettings) {

    private val enabledOperations = playSettings.getEnabledOperations()
    private var randomOperationIndex: Int = 0
    private var task: Task? = null

    fun generateTask(): Task {
        val operation = if (enabledOperations.size > 1) {
            randomOperationIndex = GenerateRandomNumber.execute(0, enabledOperations.size - 1)
            enabledOperations[randomOperationIndex]
        } else {
            enabledOperations[0]
        }
        var newTask: Task
        do {
            newTask = operation.generateTask()
        } while (newTask == task)
        task = newTask
        return task!!
    }

    fun getAnswers() = enabledOperations[randomOperationIndex].generateAnswers(
        playSettings.numberAnswers,
        task!!.answer
    )

    fun getLastTask() = task!!

}