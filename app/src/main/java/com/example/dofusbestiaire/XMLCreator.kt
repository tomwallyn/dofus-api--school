package com.example.dofusbestiaire

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class XMLCreator {

    fun createXMLCard(thisContext:Context,imageUrl :String, name:String): LinearLayout {
        //Create a Parent Linear Layout
        val parentLinearLayout = LinearLayout(thisContext);
        parentLinearLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        parentLinearLayout.orientation = LinearLayout.VERTICAL

        //Create a Children Linear Layout
        val linearCH = LinearLayout(thisContext)
        linearCH.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        //Create an Image View that will be in the Children Linear Layout
        val iv = ImageView(thisContext)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        Glide.with(thisContext).load(imageUrl).apply(RequestOptions().override(1000, 400)).into(iv)
        lp.setMargins(0, 11, 7, 0)
        iv.setLayoutParams(lp)


        //Create a Text View that will be in the Children Linear Layout
        val tv1 = TextView(thisContext)
        val lptv1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        tv1.setLayoutParams(lptv1)
        tv1.setText(name) // title
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,25F)
        tv1.setTypeface(null, Typeface.BOLD)

        //Add Image view and text view to the children linear layout
        linearCH.removeAllViews()
        linearCH.addView(iv)
        linearCH.addView(tv1)

        //Add children layout to parent layout
        parentLinearLayout.removeAllViews()
        parentLinearLayout.addView(linearCH)
        return parentLinearLayout
    }
}