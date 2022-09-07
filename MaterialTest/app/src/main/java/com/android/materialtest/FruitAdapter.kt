package com.android.materialtest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FruitAdapter(val context: Context, val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val postion = holder.adapterPosition
            val fruit = fruitList.get(postion)
            val intent = Intent(context, FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fruitName.text = fruitList.get(position).name
        Glide.with(context).load(fruitList.get(position).imageId).into(holder.fruitImage)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitName: TextView = view.findViewById(R.id.fruitName)
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
    }
}