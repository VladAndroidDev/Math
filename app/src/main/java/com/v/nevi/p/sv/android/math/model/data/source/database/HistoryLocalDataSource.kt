package com.v.nevi.p.sv.android.math.model.data.source.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.v.nevi.p.sv.android.math.model.data.History
import com.v.nevi.p.sv.android.math.model.data.source.HistoryDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.v.nevi.p.sv.android.math.model.data.Result.Success
import com.v.nevi.p.sv.android.math.model.data.Result.Error
import com.v.nevi.p.sv.android.math.model.data.Result
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

class HistoryLocalDataSource(
    private val dao: HistoryDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : HistoryDataSource {
    override fun observeTasks(): LiveData<Result<List<History>>> {
        return dao.observeHistories().map {
            Success(it)
        }
    }

    override fun observeTask(date: Date): LiveData<Result<History?>> {
        return dao.observeHistoryByDate(date).map {
            Success(it)
        }
    }

    override suspend fun saveHistory(history: History) = withContext(dispatcher) {
        dao.insertHistory(history)
    }


    override suspend fun updateHistory(history: History) = withContext(dispatcher) {
        dao.updateHistory(history)
    }

    override suspend fun deleteAllHistories() = withContext(dispatcher) {
        dao.deleteHistories()
    }

    override suspend fun getHistoryByDate(date: Date): Result<History> = withContext(dispatcher) {
        try {
            val history = dao.getHistoryByDate(date)
            if(history!=null){
                return@withContext Success(history)
            }else{
                return@withContext Error(Exception("History not found with date:$date"))
            }
        }catch (e:Exception){
            return@withContext Error(e)
        }
    }
}