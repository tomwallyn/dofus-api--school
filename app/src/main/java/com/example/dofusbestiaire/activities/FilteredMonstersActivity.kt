package com.example.dofusbestiaire.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dofusbestiaire.R
import com.example.dofusbestiaire.data.ApiClient
import com.example.dofusbestiaire.models.Monsters
import com.example.dofusbestiaire.ui.RecyclerViewCardTypeCreator
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FilteredMonstersActivity : AppCompatActivity(){
    lateinit var recyclerViewCardTypeCreator: RecyclerViewCardTypeCreator
    lateinit var bottomNavigationView: BottomNavigationView
    val allMonsters = callApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filtered_monsters_layout)
        val allTypesAndImage = getAllTypes()
        recycler(allTypesAndImage)
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation)
        this.configureBottomView()
    }

    fun recycler(allTypes: MutableList<MutableList<String>>){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewType)
        recyclerViewCardTypeCreator= RecyclerViewCardTypeCreator(allTypes,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewCardTypeCreator
    }
    fun callApi(): List<Monsters> {
        var content = listOf<Monsters>()
        runBlocking {
            launch {
                try {
                    val response = ApiClient.apiService.getMonsters()

                    if (response.isSuccessful && response.body() != null) {
                        content = response.body()!!
//do something
                    } else {
                        Toast.makeText(
                            this@FilteredMonstersActivity,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(
                        this@FilteredMonstersActivity,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            return@runBlocking content
        }
        return content
    }

    fun getAllTypes(): MutableList<MutableList<String>> {
        var allTypesAndImage: MutableList<MutableList<String>> = mutableListOf()
        var allTypes :MutableList<String> = mutableListOf()
        var allImage : MutableList<String> = mutableListOf()
        for (monster in allMonsters){
            if(!allTypes.contains(monster.type)){
                allTypes.add(monster.type)
                allImage.add(monster.imgUrl)
            }
        }
        allTypesAndImage.add(allTypes)
        allTypesAndImage.add(allImage)
        return allTypesAndImage
    }

    fun configureBottomView() {
        bottomNavigationView.setOnItemReselectedListener { item -> updateMainFragment(item.itemId) }
    }

    fun updateMainFragment(integer: Int): Boolean {
        when (integer) {
            R.id.home -> {
                val intent = Intent(this, AllMonstersActivity::class.java)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                this.finish()
            }
            R.id.filters ->{
                val intent = Intent(this, FilteredMonstersActivity::class.java)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                this.finish()
            }


        }
        return true
    }


}