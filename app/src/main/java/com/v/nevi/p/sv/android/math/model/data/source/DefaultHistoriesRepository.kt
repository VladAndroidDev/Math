package com.v.nevi.p.sv.android.math.model.data.source

import androidx.lifecycle.LiveData
import com.v.nevi.p.sv.android.math.model.data.History
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.*
import com.v.nevi.p.sv.android.math.model.data.Result
import kotlinx.coroutines.coroutineScope

class DefaultHistoriesRepository(
    private val historiesLocalDataSource:HistoryDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
):HistoryRepository {

    override fun observeHistories(): LiveData<Result<List<History>>> {
        return historiesLocalDataSource.observeTasks()
    }

    override fun observeHistory(date: Date): LiveData<Result<History?>> {
        return historiesLocalDataSource.observeTask(date)
    }

    override suspend fun saveHistory(history: History) {
        coroutineScope {
            historiesLocalDataSource.saveHistory(history)
        }
    }

    override suspend fun updateHistory(history: History) {
        coroutineScope {
            historiesLocalDataSource.updateHistory(history)
        }
    }

    override suspend fun deleteAllHistories() {
        coroutineScope {
            historiesLocalDataSource.deleteAllHistories()
        }
    }

    override suspend fun getHistoryByDate(date: Date): Result<History> {
        return historiesLocalDataSource.getHistoryByDate(date)
    }
}