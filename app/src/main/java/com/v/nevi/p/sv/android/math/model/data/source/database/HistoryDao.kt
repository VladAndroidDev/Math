package com.v.nevi.p.sv.android.math.model.data.source.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.v.nevi.p.sv.android.math.model.data.History
import java.util.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun observeHistories():LiveData<List<History>>

    @Query("SELECT * FROM history WHERE date=:date")
    fun observeHistoryByDate(date: Date): LiveData<History>

    @Query("SELECT * FROM history WHERE date=:date")
    suspend fun getHistoryByDate(date: Date):History?

    @Insert
    suspend fun insertHistory(history: History)

    @Update
    suspend fun updateHistory(history: History)

    @Query("DELETE FROM history")
    suspend fun deleteHistories()

}