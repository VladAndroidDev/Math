package com.v.nevi.p.sv.android.math.model.playsettings

import com.google.gson.annotations.Expose
import com.v.nevi.p.sv.android.math.model.operations.*
import java.io.Serializable


data class PlaySettings private constructor(

    val operationsSettings: Array<OperationSettings> = arrayOf(
        OperationSettings(OperationClass.ADDITION),
        OperationSettings(OperationClass.SUBTRACTION),
        OperationSettings(OperationClass.MULTIPLICATION),
        OperationSettings(OperationClass.DIVISION)
    ),

    var gameDuration: Int = 0,

    var numberAnswers: Int = 4,

    var numberTasks: Int = 0
) : Serializable {

    fun getEnabledOperations(): List<Operation> {
        return operationsSettings.filter {
            it.checked
        }.map {
            it.createOperation()
        }
    }

    fun getNumberOperations() = operationsSettings.size

    companion object {



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