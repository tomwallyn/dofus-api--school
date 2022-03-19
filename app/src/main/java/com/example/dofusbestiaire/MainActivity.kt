package com.example.dofusbestiaire

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dofusbestiaire.data.ApiClient
import com.example.dofusbestiaire.models.Monsters
import com.example.dofusbestiaire.ui.RecyclerViewCardCreator
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    lateinit var recyclerViewCardCreator: RecyclerViewCardCreator
    lateinit var adapter: ListViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_monsters_layout)
        val allMonsters = callApi()
        recycler(allMonsters)
        adapter = ListViewAdapter(this, allMonsters, recyclerViewCardCreator)
        val editsearch = findViewById<SearchView>(R.id.search);
        editsearch.setOnQueryTextListener(this)
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
                            this@MainActivity,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            return@runBlocking content
        }
        return content
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        val text = p0
        if (text != null) {
            adapter.filter(text)
        }
        return false
    }


}