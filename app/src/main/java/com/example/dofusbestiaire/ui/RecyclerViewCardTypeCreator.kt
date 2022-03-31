package com.example.dofusbestiaire.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dofusbestiaire.R
import com.example.dofusbestiaire.activities.AllMonstersActivity
import com.example.dofusbestiaire.activities.SameTypesMonstersActivity

class RecyclerViewCardTypeCreator(private val typesSet: MutableList<MutableList<String>>, val context:Context): RecyclerView.Adapter<RecyclerViewCardTypeCreator.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView
        val imageView: ImageView
        val textView : TextView
        init {
            cardView = view.findViewById(R.id.cardContainer)
            imageView = view.findViewById(R.id.cardImage)
            textView = view.findViewById(R.id.cardText)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout_family, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(context).load(typesSet[1][position]).into(viewHolder.imageView)
        viewHolder.textView.text = typesSet[0][position]
        viewHolder.cardView.setOnClickListener{
            val intent = Intent(context, SameTypesMonstersActivity::class.java)
            intent.putExtra("type",typesSet[0][position])
            context.startActivity(intent)

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = typesSet[0].size


}