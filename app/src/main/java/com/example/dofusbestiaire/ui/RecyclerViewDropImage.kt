package com.example.dofusbestiaire.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dofusbestiaire.R
import com.example.dofusbestiaire.activities.SameTypesMonstersActivity
import com.example.dofusbestiaire.activities.SingleMonsterActivity
import com.example.dofusbestiaire.data.FavoriteMonstersClient
import com.example.dofusbestiaire.models.Monsters
import kotlinx.android.synthetic.main.card_layout.view.*

class RecyclerViewDropImage(private val imagesSet: List<String>, val context:Context): RecyclerView.Adapter<RecyclerViewDropImage.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView : ImageView = view.findViewById(R.id.imageDrop)
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.drop_image, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val image = imagesSet[position]
        Glide.with(context).load(image).into(viewHolder.imageView)


    }

    override fun getItemCount() = imagesSet.size
}