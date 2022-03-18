package com.v.nevi.p.sv.android.math.model.generator

import android.util.Log
import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.playsettings.OperationSettings
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

class Generator(private val playSettings: PlaySettings) {

    private lateinit var operationSettings:OperationSettings

    fun generateTask(): Task {
        val enabledOperationsSettings = playSettings.getEnabledOperationSettings()
        operationSettings = if (enabledOperationsSettings.size > 1) {
            val indexRandomOperation = GenerateRandomNumber.execute(0, enabledOperationsSettings.size - 1)
            enabledOperationsSettings[indexRandomOperation]
        } else {
            enabledOperationsSettings[0]
        }
        return operationSettings.operation.generateTask(operationSettings.valueStartRange,operationSettings.valueEndRange)
    }

    fun getAnswers() = operationSettings.operation.generateAnswers(playSettings.numberAnswers)

    fun getLastTask() = operationSettings.operation.task!!

}