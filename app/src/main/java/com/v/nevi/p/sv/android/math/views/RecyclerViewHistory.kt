package com.v.nevi.p.sv.android.math.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.math.model.StatisticsItem
import com.v.nevi.p.sv.android.math.screens.play.adapters.HistoryAdapter

class RecyclerViewHistory(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
) : RecyclerView(
    context, attributeSet,
    defStyleAttr,
) {
    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        0
    )

    constructor(context: Context) : this(
        context,
        null
    )

    init {
        val lm = LinearLayoutManager(context).apply {
            stackFromEnd = true
        }
        layoutManager = lm
//        addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val firstVisibleItemPosition: Int = lm.findFirstVisibleItemPosition()
//                val lastVisibleItemPosition: Int = lm.findLastVisibleItemPosition()
//                val countVisibleItems = lastVisibleItemPosition - firstVisibleItemPosition
//                var counter = 0
//                while (counter < lastVisibleItemPosition) {
//                    val view = lm.findViewByPosition(firstVisibleItemPosition + counter)
//                    view?.alpha =
//                        counter / countVisibleItems.toFloat() + 0.02f
//                    counter++
//                }
//            }
//        })
    }

    fun insert(itemPlayTasks: StatisticsItem.ItemPlayHistory){
        val historyAdapter = adapter as HistoryAdapter
        historyAdapter.insertInStart(itemPlayTasks)
        layoutManager?.scrollToPosition(adapter!!.itemCount-1)
    }
}