package com.v.nevi.p.sv.android.math.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(val answer:Int, private val stringRepresentation:String):Parcelable {

    override fun toString(): String {
        return stringRepresentation
    }
}