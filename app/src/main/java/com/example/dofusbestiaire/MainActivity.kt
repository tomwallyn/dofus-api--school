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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val container = findViewById<LinearLayout>(R.id.container)
        container.removeAllViews()
        container.addView(createXMLElement("https://s.ankama.com/www/static.ankama.com/dofus/www/game/items/200/18006.png","romain"))
        container.addView(createXMLElement("https://s.ankama.com/www/static.ankama.com/dofus/www/game/items/200/18006.png","romain"))

    }
    fun createXMLElement(imageUrl :String, name:String): LinearLayout {
        val parent = LinearLayout(this);
        parent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        parent.orientation = LinearLayout.VERTICAL


        val linearCH = LinearLayout(this)
        linearCH.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        val iv = ImageView(this)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        Glide.with(this).load(imageUrl).apply(RequestOptions().override(1000, 400)).into(iv)

        lp.setMargins(0, 11, 7, 0)
        iv.setLayoutParams(lp)
        // TextView1
        val tv1 = TextView(this)
        val lptv1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        tv1.setLayoutParams(lptv1)
        tv1.setText(name) // title
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,25F)
        tv1.setTypeface(null, Typeface.BOLD)



        linearCH.removeAllViews()
        linearCH.addView(iv)
        linearCH.addView(tv1)


        parent.removeAllViews()
        parent.addView(linearCH)
        return parent
    }

}