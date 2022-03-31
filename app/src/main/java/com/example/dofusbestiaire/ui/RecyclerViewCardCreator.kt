package com.example.dofusbestiaire.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dofusbestiaire.R
import com.example.dofusbestiaire.activities.SameTypesMonstersActivity
import com.example.dofusbestiaire.activities.SingleMonsterActivity
import com.example.dofusbestiaire.models.Monsters

class RecyclerViewCardCreator(private val monstersSet: List<Monsters>, val context:Context): RecyclerView.Adapter<RecyclerViewCardCreator.ViewHolder>()  {
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
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val image = monstersSet[position].imgUrl

        Glide.with(context).load(image).into(viewHolder.imageView)
        viewHolder.textView.text = monstersSet[position].name

        viewHolder.cardView.setOnClickListener{
            val intent = Intent(context, SingleMonsterActivity::class.java)
            intent.putExtra("id",monstersSet[position]._id)
            context.startActivity(intent)

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = monstersSet.size
}