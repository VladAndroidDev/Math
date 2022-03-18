package com.v.nevi.p.sv.android.math.model.playsettings

import com.v.nevi.p.sv.android.math.model.operations.*
import java.io.Serializable

data class PlaySettings private constructor(
    val arrayOperationSettings: Array<OperationSettings> = arrayOf(OperationSettings(AdditionOperation()),
        OperationSettings(SubtractionOperation()),
        OperationSettings(MultiplicationOperation()),
    OperationSettings(DivisionOperation())),
    var gameDuration: Int = 600,
    var numberAnswers: Int = 0,
    var numberTasks: Int = 0
) : Serializable {

    fun getEnabledOperationSettings(): List<OperationSettings> {
        return arrayOperationSettings.filter {
            it.checked
        }
    }

    companion object {

        const val NUMBER_OPERATIONS = 4

        private var instance: PlaySettings? = null

        fun initialize(playSettings: PlaySettings?) {
            instance = playSettings
        }

        fun getInstance(): PlaySettings {
            return instance ?: PlaySettings()
        }
    }

}