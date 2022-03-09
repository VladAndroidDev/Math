package com.v.nevi.p.sv.android.math.model.playsettings

import com.v.nevi.p.sv.android.math.model.operations.*
import java.io.Serializable

data class PlaySettings private constructor(
    var additionEnabled: Boolean = true,
    var subtractionEnabled: Boolean = true,
    var multiplicationEnabled: Boolean = true,
    var divisionEnabled: Boolean = true,
    var valueStartRange: Int = 0,
    var valueEndRange: Int = 50,
    var gameDuration: Int = 600,
    var numberAnswers: Int = 0,
    var numberTasks: Int = 0
):Serializable {

    fun getEnabledOperations():List<Operation>{
            val list:MutableList<Operation> = mutableListOf()
            if (additionEnabled) {
                list.add(AdditionOperation())
            }
            if(subtractionEnabled){
                list.add(SubtractionOperation())
            }
            if (divisionEnabled){
                list.add(DivisionOperation())
            }
            if(multiplicationEnabled){
                list.add(MultiplicationOperation())
            }
        return list
    }

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