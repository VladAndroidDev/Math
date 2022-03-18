package com.v.nevi.p.sv.android.math.model.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.v.nevi.p.sv.android.math.model.data.History

@Database(version = 1, entities = [History::class])
@TypeConverters(Converters::class)
abstract class HistoryDatabase:RoomDatabase() {

    abstract fun historiesDao(): HistoryDao

}