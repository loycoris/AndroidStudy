package com.android.jetpackstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            //WORD_JSON_FILE_NAME是你的Json文件名
            applicationContext.assets.open("WORD_JSON_FILE_NAME").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    //创建数据类型，Word是基本的数据单元，是自己创建的类，是自定义的数据结构
                    val wordType: Type = object : TypeToken<List<String>>() {}.type
                    //解析
//                    val wordList: List<String> = Gson().fromJson(jsonReader,wordType)

                }
            }
        } catch (ex: Exception) {
//            Log.e(TAG, "Error seeding database", ex)
        }

    }
}