package com.example.dofusbestiaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<ImageView>(R.id.testImage)
        Glide.with(view).load("https://s.ankama.com/www/static.ankama.com/dofus/www/game/items/200/18006.png").into(view)
    }

}