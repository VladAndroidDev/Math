package com.v.nevi.p.sv.android.math

import android.content.Context
import androidx.room.Room
import com.v.nevi.p.sv.android.math.model.data.source.DefaultHistoriesRepository
import com.v.nevi.p.sv.android.math.model.data.source.HistoryDataSource
import com.v.nevi.p.sv.android.math.model.data.source.HistoryRepository
import com.v.nevi.p.sv.android.math.model.data.source.database.HistoryDatabase
import com.v.nevi.p.sv.android.math.model.data.source.database.HistoryLocalDataSource

object ServiceLocator {

    private var database: HistoryDatabase?=null
    private var repository: HistoryRepository?=null

    fun provideHistoryRepository(context: Context): HistoryRepository {
        return repository?: createHistoriesRepository(context)
    }

    private fun createHistoriesRepository(context: Context): HistoryRepository {
        val newRepo = DefaultHistoriesRepository(createHistoryLocalDataSource(context))
        repository = newRepo
        return newRepo
    }

    private fun createHistoryLocalDataSource(context: Context): HistoryDataSource {
        val database = database?:createDataBase(context)
        return HistoryLocalDataSource(database.historiesDao())
    }

    private fun createDataBase(context: Context): HistoryDatabase {
        val newDatabase = Room.databaseBuilder(context,HistoryDatabase::class.java,"Histories.db").build()
        database = newDatabase
        return newDatabase
    }


}