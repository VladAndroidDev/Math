package com.v.nevi.p.sv.android.math.model.data.source

import androidx.lifecycle.LiveData
import com.v.nevi.p.sv.android.math.model.data.History
import java.util.*
import com.v.nevi.p.sv.android.math.model.data.Result

interface HistoryDataSource {

    fun observeTasks():LiveData<Result<List<History>>>

    fun observeTask(date: Date):LiveData<Result<History?>>

    suspend fun saveHistory(history: History)

    suspend fun updateHistory(history: History)

    suspend fun deleteAllHistories()

    suspend fun getHistoryByDate(date: Date):Result<History>

}