package com.android.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.room.databinding.ItemBinding

class RecycleViewAdapter(var list: List<Student>) :
    RecyclerView.Adapter<RecycleViewAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = DataBindingUtil.inflate<ItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class StudentViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}
