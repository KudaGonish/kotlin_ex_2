package com.kudagonish.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kudagonish.models.DataModel
import com.kudagonish.models.databinding.ItemWordBinding

class HistoryAdapter: androidx.recyclerview.widget.ListAdapter<DataModel, HistoryAdapter.HistoryViewHolder>(
    MainCallback
) {


    inner class HistoryViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(ItemWordBinding.inflate(parent.inflater(), parent, false).root) {

        fun bind(data: DataModel) {
            val binder = ItemWordBinding.bind(itemView)
            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text = data.meaning?.firstOrNull()?.translation?.translation

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    private fun ViewGroup.inflater() = LayoutInflater.from(context)
}

object MainCallback : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

}