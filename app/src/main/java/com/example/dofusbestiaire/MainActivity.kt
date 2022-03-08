package com.example.dofusbestiaire

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dofusbestiaire.data.ApiService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val container = findViewById<LinearLayout>(R.id.container)
        container.removeAllViews()
        val xmlCreator = XMLCreator()
        container.addView(xmlCreator.createXMLCard(this,"https://s.ankama.com/www/static.ankama.com/dofus/www/game/items/200/18006.png","romain"))
    }




}