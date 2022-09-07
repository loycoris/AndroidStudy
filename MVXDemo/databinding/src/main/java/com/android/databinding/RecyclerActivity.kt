package com.android.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.databinding.databinding.ActivityRecyclerBinding
import com.android.databinding.model.Swordsman
import androidx.recyclerview.widget.LinearLayoutManager


class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = SwordsmanAdapter(getList())
    }

    private fun getList(): List<Swordsman> {
        val list = listOf(
            Swordsman("杨影枫", "SS"), Swordsman("月眉儿", "A"),
            Swordsman("1111", "SS"), Swordsman("2222", "SS"),
            Swordsman("3333", "SS"), Swordsman("4444", "SS")
        )
        return list
    }
}