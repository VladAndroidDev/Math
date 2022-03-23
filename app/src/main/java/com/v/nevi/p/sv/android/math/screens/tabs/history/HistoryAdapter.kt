package com.v.nevi.p.sv.android.math.screens.tabs.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.math.databinding.HeaderHistoryBinding
import com.v.nevi.p.sv.android.math.databinding.ItemHistoryBinding
import com.v.nevi.p.sv.android.math.model.data.History
import com.v.nevi.p.sv.android.math.utils.createStringTimeRepresentation

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1

class HistoryAdapter(private val list: List<History>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            val binding = HeaderHistoryBinding.inflate(inflater, parent, false)
            HeaderHistoryViewHolder(binding)
        } else {
            val binding = ItemHistoryBinding.inflate(inflater, parent, false)
            HistoryViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HistoryViewHolder) {
            holder.bind(list[position - 1])
        } else {
            holder as HeaderHistoryViewHolder
            var time = 0L
            var totalTasks = 0L
            var correctAnswers = 0L
            for (itemList in list) {
                time += itemList.time
                totalTasks += itemList.numberTasks
                correctAnswers += itemList.numberCorrectAnswers
            }
            val quality = "${(correctAnswers.toDouble() / totalTasks * 100).toInt()}%"
            val timeStr = if (time > 0) {

                createStringTimeRepresentation(time)
            } else {
                "00:00"
            }
            val averageTime = if (totalTasks == 0L) {
                "0"
            } else {
                createStringTimeRepresentation(time / totalTasks)
            }
            val headerItem = HeaderItemHistoryList(
                timeStr,
                totalTasks.toString(),
                correctAnswers.toString(),
                averageTime,
                quality
            )
            holder.bind(headerItem)
        }
    }

    override fun getItemCount() = list.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: History) {
            binding.item = item
        }
    }

    class HeaderHistoryViewHolder(private val binding: HeaderHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HeaderItemHistoryList) {
            binding.item = item
        }
    }
}

class HeaderItemHistoryList(
    val timeInGame: String,
    val totalTasks: String,
    val correctAnswers: String,
    val averageTime: String,
    val quality: String
)
