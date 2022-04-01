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
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val image = monstersSet[position].imgUrl
        val favoriteMonstersClient = FavoriteMonstersClient(context)

        Glide.with(context).load(image).into(viewHolder.imageView)
        viewHolder.textView.text = monstersSet[position].name

        viewHolder.cardView.button.setOnClickListener{
            val monsterId = monstersSet[position]._id
            val favoriteMonsters = favoriteMonstersClient.getFavoriteMonsters()
            if (!favoriteMonsters.contains( monsterId.toString())){
                favoriteMonstersClient.saveFavoriteMonster(monsterId)
            }
            else{
                favoriteMonstersClient.deleteFavoriteMonster(monsterId)
            }
            viewHolder.cardView.button.iconTint = if(isMonsterInFavorite(monstersSet[position]._id)) ContextCompat.getColorStateList(context, R.color.gold) else ContextCompat.getColorStateList(context, R.color.white)
            viewHolder.cardView.button.backgroundTintList = if(isMonsterInFavorite(monstersSet[position]._id)) ContextCompat.getColorStateList(context, R.color.white) else ContextCompat.getColorStateList(context, R.color.gold)
        }
        viewHolder.cardView.button.iconTint = if(isMonsterInFavorite(monstersSet[position]._id)) ContextCompat.getColorStateList(context, R.color.gold) else ContextCompat.getColorStateList(context, R.color.white)
        viewHolder.cardView.button.backgroundTintList = if(isMonsterInFavorite(monstersSet[position]._id)) ContextCompat.getColorStateList(context, R.color.white) else ContextCompat.getColorStateList(context, R.color.gold)
        viewHolder.cardView.setOnClickListener{
            val intent = Intent(context, SingleMonsterActivity::class.java)
            intent.putExtra("id",monstersSet[position]._id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount() = monstersSet.size

    fun isMonsterInFavorite(id:Int):Boolean{
        val favoriteMonstersClient = FavoriteMonstersClient(context)
        val favoriteMonsters = favoriteMonstersClient.getFavoriteMonsters()
        return favoriteMonsters.contains(id.toString())
    }
}