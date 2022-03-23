package com.example.dofusbestiaire

import android.R
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomViewController( private val bottomNavigationView: BottomNavigationView, private val mainActivity: MainActivity) {

    private fun configureBottomView() {
      this.bottomNavigationView.setOnItemSelectedListener { item -> updatePage(item.itemId) }

    }


    private fun updatePage(integer: Int): Boolean {
        when (integer) {
            R.id.home -> this.mainActivity.changeActivity("home")

        }
        return true
    }
}