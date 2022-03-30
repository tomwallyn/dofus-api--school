package com.example.dofusbestiaire.components

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.dofusbestiaire.R
import com.example.dofusbestiaire.models.Monsters
import com.example.dofusbestiaire.ui.RecyclerViewCardCreator
import java.util.*
import kotlin.collections.ArrayList


class ListViewAdapter(context: Context, allMonsters: List<Monsters>,
                      private var recyclerViewCardCreator: RecyclerViewCardCreator
) : BaseAdapter() {
    private var mContext: Context = context
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
                .inflate(R.layout.listview_item, null)
            holder.name = view.findViewById<TextView>(R.id.name);
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        // Set the results into TextViews
        holder.name?.text  = monstersNameList[position].name
        return view
    }

    fun returnFilteredList(charText: String): MutableList<Monsters> {
        var charText = charText
        charText = charText.lowercase(Locale.getDefault())
        monstersNameList.clear()
        return if (charText.isEmpty()) {
            monstersNameList.addAll(arraylist!!)
            monstersNameList
        } else {
            for (wp in arraylist!!) {
                if (wp.name.lowercase(Locale.getDefault()).contains(charText)) {
                    monstersNameList.add(wp)
                }
            }
            monstersNameList
        }
    }

}