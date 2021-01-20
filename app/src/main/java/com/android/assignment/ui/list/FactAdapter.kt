package com.android.assignment.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.assignment.data.model.Fact
import com.android.assignment.databinding.LayoutRowItemBinding

class FactAdapter: ListAdapter<Fact,FactAdapter.FactViewHolder>(FACT_COMPARATOR){

    companion object{
        private val FACT_COMPARATOR = object : DiffUtil.ItemCallback<Fact>() {
            override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FactViewHolder{
        return FactViewHolder(LayoutRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class FactViewHolder(private val binding:LayoutRowItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bindData(fact: Fact) {
            binding.fact = fact
        }

    }
}