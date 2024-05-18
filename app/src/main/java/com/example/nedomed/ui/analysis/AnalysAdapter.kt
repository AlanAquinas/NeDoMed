package com.example.nedomed.ui.analysis

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nedomed.databinding.AnalysItemBinding


import com.example.nedomed.network.model.AnalysResponse

class AnalysAdapter(private val onAnalysClicked: (AnalysResponse) -> Unit) :
    ListAdapter<AnalysResponse, AnalysAdapter.AnalysViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysViewHolder {
        val binding = AnalysItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnalysViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnalysViewHolder, position: Int) {
        val analys = getItem(position)
        analys?.let {
            holder.bind(it)
        }
    }

    inner class AnalysViewHolder(private val binding: AnalysItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(analys: AnalysResponse) {
            binding.analysIdTextView.text = analys.id.toString()
            binding.analysTypeTextView.text = analys.testType
            binding.root.setOnClickListener {
                onAnalysClicked(analys)
            }
            binding.conclusionText.text = analys.conclusion
            binding.dateText.text = analys.date
            binding.detailText.setText(analys.analysisDetails.replace("\\\n", System.getProperty("line.separator")))
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<AnalysResponse>() {
        override fun areItemsTheSame(oldItem: AnalysResponse, newItem: AnalysResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnalysResponse, newItem: AnalysResponse): Boolean {
            return oldItem == newItem
        }
    }
}