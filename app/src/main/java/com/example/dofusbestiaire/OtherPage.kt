package com.example.dofusbestiaire

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dofusbestiaire.ui.RecyclerViewCardCreator

class OtherPage: AppCompatActivity() {
    lateinit var recyclerViewCardCreator: RecyclerViewCardCreator
    lateinit var adapter: ListViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_page)

    }


}