package com.android.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.databinding.model.ObSwordsman

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.android.databinding.databinding.ActivityUpdateBinding
import com.android.databinding.model.ObFSwordsman
import com.android.databinding.model.Swordsman


class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var obSwordsman: ObSwordsman
    lateinit var obfSwordsman: ObFSwordsman

    //    lateinit var list: ObservableArrayList<Swordsman>
    lateinit var swordsman1: Swordsman
    lateinit var swordsman2: Swordsman

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
        obSwordsman = ObSwordsman("任我行", "A")
        binding.obswordsman = obSwordsman

        obfSwordsman = ObFSwordsman("风清扬", "S")
        binding.setObfswordsman(obfSwordsman)

        swordsman1 = Swordsman("张无忌", "S")
        swordsman2 = Swordsman("周芷若", "B")
        val list = ObservableArrayList<Swordsman>().apply {
            add(swordsman1)
            add(swordsman2)
        }
//        list.add(swordsman1)
//        list.add(swordsman2)
        binding.list = list

        binding.btUpdateObswordsman.setOnClickListener {
            obSwordsman.name = "东方不败"
        }

        binding.btUpdateObfswordsman.setOnClickListener {
            obfSwordsman.name.set("令狐冲")
        }

        binding.btUpdateObmap.setOnClickListener {
            swordsman1.name = "杨过";
            swordsman2.name = "小龙女"
            list.add(swordsman1)
            list.add(swordsman2)
        }

        binding.btUpdateBind.setOnClickListener {
            obSwordsman.name = "任我行"
        }
    }
}