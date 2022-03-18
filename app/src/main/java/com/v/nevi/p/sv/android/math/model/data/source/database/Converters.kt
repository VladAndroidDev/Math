package com.v.nevi.p.sv.android.math.model.data.source.database

import androidx.room.TypeConverter
import java.util.*


class Converters {

    @TypeConverter
    fun fromDate(date: Date?):Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(long: Long?):Date?{
        return long?.let {
            Date(it)
        }
    }
}