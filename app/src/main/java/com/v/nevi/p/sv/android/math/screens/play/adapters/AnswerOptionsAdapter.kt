package com.v.nevi.p.sv.android.math.screens.play.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.math.databinding.ItemAnswerOptionsListBinding
import com.v.nevi.p.sv.android.math.screens.play.PlayViewModel
import com.v.nevi.p.sv.android.math.screens.play.adapters.AnswerOptionsAdapter.AnswerOptionsViewHolder as AnswerOptionsViewHolder

class AnswerOptionsAdapter(private val viewModel: PlayViewModel) : RecyclerView.Adapter<AnswerOptionsViewHolder>() {

    var listItems:List<Int> = emptyList()
    set(value) {
        field =value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerOptionsViewHolder {
        return from(parent,viewModel)
    }

    override fun onBindViewHolder(holder: AnswerOptionsViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    companion object {
        fun from(parent: ViewGroup,viewModel: PlayViewModel): AnswerOptionsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemAnswerOptionsListBinding.inflate(inflater, parent, false)
            binding.viewmodel = viewModel
            return AnswerOptionsViewHolder(binding)
        }
    }

    class AnswerOptionsViewHolder(private val binding: ItemAnswerOptionsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(answer:Int){
                binding.answerOptionsButton.text = answer.toString()
            }
    }
}