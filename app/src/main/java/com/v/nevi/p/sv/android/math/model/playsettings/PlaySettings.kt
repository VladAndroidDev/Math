package com.v.nevi.p.sv.android.math.model.playsettings

import java.io.Serializable

data class PlaySettings private constructor(
    var additionEnabled: Boolean = true,
    var subtractionEnabled: Boolean = true,
    var multiplicationEnabled: Boolean = true,
    var divisionEnabled: Boolean = true,
    var valueStartRange: Int = 0,
    var valueEndRange: Int = 50,
    var gameDurationMin: Int = 10,
    var numberAnswers: Int = 0,
    var numberTasks: Int = 0
):Serializable {
    companion object {

        private var instance: PlaySettings? = null

        fun initialize(playSettings: PlaySettings?){
            instance = playSettings
        }
        fun getInstance(): PlaySettings {
            return instance ?: PlaySettings()
        }
    }

}