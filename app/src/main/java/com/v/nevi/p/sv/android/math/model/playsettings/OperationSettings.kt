package com.v.nevi.p.sv.android.math.model.playsettings

import com.google.gson.annotations.Expose
import com.v.nevi.p.sv.android.math.model.operations.*
import java.io.Serializable

enum class OperationClass{
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION
}

class OperationSettings(

    var operationClass: OperationClass,

    var checked: Boolean = true,

    var valueStartRange: Int = 0,

    var valueEndRange: Int = 50
) : Serializable {

    fun createOperation():Operation {
        return when(operationClass){
            OperationClass.ADDITION -> AdditionOperation(valueStartRange,valueEndRange)
            OperationClass.SUBTRACTION -> SubtractionOperation(valueStartRange,valueEndRange)
            OperationClass.MULTIPLICATION -> MultiplicationOperation(valueStartRange,valueEndRange)
            OperationClass.DIVISION-> DivisionOperation(valueStartRange,valueEndRange)
        }
    }
}