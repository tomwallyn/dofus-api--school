package com.example.dofusbestiaire.activities

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dofusbestiaire.R
import com.example.dofusbestiaire.components.ListViewAdapter
import com.example.dofusbestiaire.data.ApiClient
import com.example.dofusbestiaire.data.FavoriteMonstersClient
import com.example.dofusbestiaire.models.Monsters
import com.example.dofusbestiaire.ui.RecyclerViewCardCreator
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AllMonstersActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var recyclerViewCardCreator: RecyclerViewCardCreator
    lateinit var adapter: ListViewAdapter
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//// example d'utilisation du FavoriteMonstersClient
//        var favoriteMonstersClient = FavoriteMonstersClient(this)
//        favoriteMonstersClient.saveFavoriteMonster(100)
//        favoriteMonstersClient.deleteFavoriteMonster(100)
//        var test = favoriteMonstersClient.getFavoriteMonsters()

        setContentView(R.layout.all_monsters_layout)
        val allMonsters = callApi()
        recycler(allMonsters)
        adapter = ListViewAdapter(this, allMonsters, recyclerViewCardCreator)
        val editsearch = findViewById<SearchView>(R.id.search);
        editsearch.setOnQueryTextListener(this)
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation)
        this.configureBottomView()
    }

    fun recycler(allMonsters:List<Monsters>){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMonster)
        recyclerViewCardCreator= RecyclerViewCardCreator(allMonsters,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewCardCreator
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
                            this@AllMonstersActivity,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(
                        this@AllMonstersActivity,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            return@runBlocking content
        }
        return content
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

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        val text = p0
        if (text !=null){
            recycler(adapter.returnFilteredList(text))
        }
        return true
    }



}