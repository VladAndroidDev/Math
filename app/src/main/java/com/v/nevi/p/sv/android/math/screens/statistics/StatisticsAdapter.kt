package com.v.nevi.p.sv.android.math.screens.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.math.databinding.ItemStatisticsRecyclerViewBinding
import com.v.nevi.p.sv.android.math.model.StatisticsItem

class StatisticsAdapter(private val list:List<StatisticsItem>):RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    companion object{
        fun from(parent: ViewGroup):StatisticsViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemStatisticsRecyclerViewBinding.inflate(inflater,parent,false)
            return StatisticsViewHolder(binding)
        }
    }
    class StatisticsViewHolder(private val binding:ItemStatisticsRecyclerViewBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: StatisticsItem){
            binding.item = item
        }
    }
}