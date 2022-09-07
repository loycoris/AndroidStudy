package com.android.materialtest

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.materialtest.databinding.ActivityFruitBinding
import com.bumptech.glide.Glide

class FruitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFruitBinding

    companion object {
        val FRUIT_NAME: String = "fruit_name"
        val FRUIT_IMAGE_ID: String = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fruit)

        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(binding.fruitImageView)
        binding.fruitContentText.text = fruitName.repeat(500)

        binding.fruitContentText.text.apply {  }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}