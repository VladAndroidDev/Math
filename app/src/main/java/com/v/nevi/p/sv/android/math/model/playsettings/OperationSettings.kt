package com.v.nevi.p.sv.android.math.model.playsettings

import com.google.gson.annotations.Expose
import com.v.nevi.p.sv.android.math.model.operations.AdditionOperation
import com.v.nevi.p.sv.android.math.model.operations.Operation
import java.io.Serializable


class OperationSettings(
    var operationClass: Class<out Operation>?,
    @Expose
    var checked: Boolean = true,
    @Expose
    var valueStartRange: Int = 0,
    @Expose
    var valueEndRange: Int = 50
) : Serializable {

    fun createOperation():Operation {
        return operationClass!!.getConstructor(Int::class.java,Int::class.java).newInstance(valueStartRange,valueEndRange)
    }
}