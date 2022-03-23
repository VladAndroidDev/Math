package com.v.nevi.p.sv.android.math.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Task(val answer:Long, private val stringRepresentation:String):Parcelable {

    override fun toString(): String {
        return stringRepresentation
    }
}