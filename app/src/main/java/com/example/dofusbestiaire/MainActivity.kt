package com.example.dofusbestiaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val imageView = findViewById<ImageView>(R.id.cardImage)
        Glide.with(imageView).load("https://s.ankama.com/www/static.ankama.com/dofus/www/game/items/200/18006.png").into(imageView)
        val textView = findViewById<TextView>(R.id.cardText)
        
    }

}