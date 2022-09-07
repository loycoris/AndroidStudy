package com.android.databinding

import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.databinding.ItemSwordsmanBinding
import com.android.databinding.model.Swordsman
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import android.view.ViewGroup


class SwordsmanAdapter(private val mList: List<Swordsman>) :
    RecyclerView.Adapter<SwordsmanAdapter.SwordsmanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwordsmanViewHolder {
        val binding = DataBindingUtil.inflate<ItemSwordsmanBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_swordsman,
            parent,
            false
        )
        return SwordsmanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SwordsmanViewHolder, position: Int) {
        val swordsman = mList[position]
        holder.binding.swordsman = swordsman
        //也可以使用下面的方式，但上面的方式较为直观
//        holder.binding.setVariable(BR.swordsman,swordsman)
//        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class SwordsmanViewHolder(val binding: ItemSwordsmanBinding) :
        RecyclerView.ViewHolder(binding.root)
}