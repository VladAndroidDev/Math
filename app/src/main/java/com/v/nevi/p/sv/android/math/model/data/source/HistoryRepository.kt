package com.v.nevi.p.sv.android.math.model.data.source

import androidx.lifecycle.LiveData
import com.v.nevi.p.sv.android.math.model.data.History
import java.util.*
import com.v.nevi.p.sv.android.math.model.data.Result

interface HistoryRepository {

    fun observeHistories():LiveData<Result<List<History>>>

    fun observeHistory(date: Date):LiveData<Result<History?>>

    suspend fun saveHistory(history: History)

    suspend fun updateHistory(history: History)

    suspend fun deleteAllHistories()

    suspend fun getHistoryByDate(date: Date):Result<History>

}