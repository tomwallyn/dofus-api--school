package com.example.dofusbestiaire.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dofusbestiaire.R
import com.example.dofusbestiaire.data.ApiClient
import com.example.dofusbestiaire.models.Monsters
import com.example.dofusbestiaire.ui.RecyclerViewCardCreator
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SingleMonsterActivity() : AppCompatActivity() {


    lateinit var recyclerViewCardCreator: RecyclerViewCardCreator
    lateinit var bottomNavigationView: BottomNavigationView
    val allMonsters = callApi()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = this.intent
        val id = intent.getIntExtra("id", 0)
        setContentView(R.layout.activity_single_monster)
        val monster = allMonsters.first { it._id == id}
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation)
        this.configureBottomView()
        val monsterNameView = findViewById<TextView>(R.id.monsterName)
        monsterNameView.text = monster.name
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
                            this@SingleMonsterActivity,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(
                        this@SingleMonsterActivity,
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
        var allTypes: MutableList<String> = mutableListOf()
        var allImage: MutableList<String> = mutableListOf()
        for (monster in allMonsters) {
            if (!allTypes.contains(monster.type)) {
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
            R.id.favorite -> {
                val intent = Intent(this, FavoriteMonstersActivity::class.java)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                this.finish()
            }
            R.id.home -> {
                val intent = Intent(this, AllMonstersActivity::class.java)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                this.finish()
            }
            R.id.filters -> {
                val intent = Intent(this, FilteredMonstersActivity::class.java)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                this.finish()
            }


        }
        return true
    }


}