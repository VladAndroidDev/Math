package com.v.nevi.p.sv.android.math.model.operations

import com.v.nevi.p.sv.android.math.model.Task

interface Operation {

    var task:Task?

    fun generateTask(startRange:Int,endRange: Int): Task

    fun generateAnswers(numberAnswers:Int):List<Long>

    fun getOperationSymbol():Char

    fun getStringRepresentation(firstValue:Int,secondValue:Int,answer:Int):String{
        return String.format("$firstValue ${getOperationSymbol()} $secondValue = ")
    }
}