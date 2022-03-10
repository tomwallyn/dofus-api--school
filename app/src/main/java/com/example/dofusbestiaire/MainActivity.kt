package com.example.dofusbestiaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dofusbestiaire.data.ApiClient
import com.example.dofusbestiaire.models.Monsters
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewCreator: RecyclerViewCreator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val allMonsters = callApi()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMonster)
        recyclerViewCreator= RecyclerViewCreator(allMonsters,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewCreator

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




}