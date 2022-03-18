package com.v.nevi.p.sv.android.math.model.playsettings

import com.v.nevi.p.sv.android.math.model.operations.Operation
import java.io.Serializable

class OperationSettings(val operation: Operation):Serializable {

    var checked = true
    var valueStartRange:Int = 0
    var valueEndRange:Int = 50

}