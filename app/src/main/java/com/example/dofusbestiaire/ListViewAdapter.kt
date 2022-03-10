package com.example.dofusbestiaire

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.dofusbestiaire.models.Monsters
import java.util.*
import kotlin.collections.ArrayList


class ListViewAdapter(context: Context?, allMonsters: List<Monsters>) : BaseAdapter() {
    var mContext: Context? = context
    var inflater: LayoutInflater? = LayoutInflater.from(mContext)
    private var monstersNameList: MutableList<Monsters> = allMonsters as MutableList<Monsters>
    private var arraylist: ArrayList<Monsters>? = ArrayList(allMonsters)

    class ViewHolder {
        var name: TextView? = null
    }

    override fun getCount(): Int {
        return monstersNameList.size
    }

    override fun getItem(position: Int): Monsters {
        return monstersNameList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var view: View? = view
        val holder: ViewHolder
        if (view == null) {
            holder = ViewHolder()
            view = LayoutInflater.from(mContext)
                .inflate(com.example.dofusbestiaire.R.layout.listview_item, null)
            holder.name = view.findViewById<TextView>(com.example.dofusbestiaire.R.id.name);
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        // Set the results into TextViews
        holder.name?.text  = monstersNameList[position].name
        return view
    }

    // Filter Class
    fun filter(charText: String) {
        var charText = charText
        charText = charText.lowercase(Locale.getDefault())
        monstersNameList!!.clear()
        if (charText.length == 0) {
            monstersNameList!!.addAll(arraylist!!)
        } else {
            for (wp in arraylist!!) {
                if (wp.name.lowercase(Locale.getDefault()).contains(charText)) {
                    monstersNameList!!.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }
}