package com.v.nevi.p.sv.android.math.screens.play.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.ItemHistoryPlayBinding
import com.v.nevi.p.sv.android.math.model.StatisticsItem
import com.v.nevi.p.sv.android.math.screens.play.PlayViewModel

class HistoryAdapter(private val viewModel: PlayViewModel) : RecyclerView.Adapter<HistoryAdapter.HistoryPlayViewHolder>() {
    private val list: MutableList<StatisticsItem.ItemPlayHistory> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryPlayViewHolder {
        return from(parent,viewModel)
    }

    override fun onBindViewHolder(holder: HistoryPlayViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun insertInStart(itemPlayTasks: StatisticsItem.ItemPlayHistory) {
        Log.d("MyTagList",list.toString())
        list.add(itemPlayTasks)
        Log.d("MyTagList",list.toString())
        notifyItemInserted(itemCount-1)
    }

    companion object{
        fun from(parent: ViewGroup,viewModel: PlayViewModel):HistoryPlayViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHistoryPlayBinding.inflate(inflater,parent,false).apply {
                viewmodel = viewModel
            }
            return HistoryPlayViewHolder(binding)
        }
    }

    class HistoryPlayViewHolder(private val binding: ItemHistoryPlayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StatisticsItem.ItemPlayHistory) {
            binding.historyItem.text = item.answer
            val id = if (item.isAnswerCorrect) {
                R.drawable.ic_baseline_check_24
            } else {
                R.drawable.ic_baseline_clear_24
            }
            binding.historyItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, id, 0)
        }
    }
}