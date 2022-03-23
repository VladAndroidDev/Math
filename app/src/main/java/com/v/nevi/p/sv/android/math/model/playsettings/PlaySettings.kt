package com.v.nevi.p.sv.android.math.model.playsettings

import com.google.gson.annotations.Expose
import com.v.nevi.p.sv.android.math.model.operations.*
import java.io.Serializable


data class PlaySettings private constructor(
    @Expose
    val operationsSettings: Array<OperationSettings> = arrayOf(
        OperationSettings(AdditionOperation::class.java),
        OperationSettings(SubtractionOperation::class.java),
        OperationSettings(MultiplicationOperation::class.java),
        OperationSettings(DivisionOperation::class.java)
//        OperationSettings(),
//        OperationSettings(),
//        OperationSettings(),
//        OperationSettings()
    ),
    @Expose
    var gameDuration: Int = 600,
    @Expose
    var numberAnswers: Int = 0,
    @Expose
    var numberTasks: Int = 0
) : Serializable {

    fun getEnabledOperations(): List<Operation> {
        return operationsSettings.filter {
            it.checked
        }.map {
            it.createOperation()
        }
    }

    companion object {

        const val NUMBER_OPERATIONS = 4

        private var instance: PlaySettings? = null

        fun initialize(playSettings: PlaySettings?) {
            instance = playSettings
        }

        fun getInstance(): PlaySettings {
            if (instance == null) {
                instance = PlaySettings()
            }
            return instance!!
        }
    }
}