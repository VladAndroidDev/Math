package com.v.nevi.p.sv.android.math.model.generator

import android.util.Log
import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.operations.Operation
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

class Generator(private val playSettings: PlaySettings) {

    private lateinit var currentOperation:Operation

    fun generateTask(): Task {
        val enabledOperations = playSettings.getEnabledOperations()
        Log.d("MyTag",enabledOperations.toString())
        currentOperation = if (enabledOperations.size > 1) {
            val indexRandomOperation = GenerateRandomNumber.execute(0, enabledOperations.size - 1)
            enabledOperations[indexRandomOperation]
        } else {
            enabledOperations[0]
        }
            return currentOperation.generateTask(
                playSettings.valueStartRange,
                playSettings.valueEndRange
            )
    }

    fun getAnswers() = currentOperation.generateAnswers(playSettings.numberAnswers)

    fun getLastTask() = currentOperation.task!!

}