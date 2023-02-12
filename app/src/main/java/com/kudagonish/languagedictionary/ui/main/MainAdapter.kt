package com.kudagonish.languagedictionary.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudagonish.models.DataModel


import com.kudagonish.models.databinding.ItemWordBinding

class MainAdapter(
    private val itemClickListener: (DataModel) -> Unit
) : ListAdapter<DataModel, MainAdapter.MainViewHolder>(MainCallback) {


    inner class MainViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(ItemWordBinding.inflate(parent.inflater(), parent, false).root) {

        fun bind(data: DataModel) {
            val binder = ItemWordBinding.bind(itemView)
            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text = data.meaning?.firstOrNull()?.translation?.translation

            binder.root.setOnClickListener{ itemClickListener(data)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
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