package com.example.dofusbestiaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.example.dofusbestiaire.data.ApiClient
import com.example.dofusbestiaire.models.Monsters
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val container = findViewById<LinearLayout>(R.id.container)
        container.removeAllViews()
        val xmlCreator = XMLCreator()
        val allMonsters = callApi()
        for (monster in allMonsters){
            container.addView(xmlCreator.createXMLCard(this,monster.imgUrl,monster.name))
        }
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