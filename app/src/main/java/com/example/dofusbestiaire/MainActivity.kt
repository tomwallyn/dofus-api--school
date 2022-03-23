package com.example.dofusbestiaire

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dofusbestiaire.data.ApiClient
import com.example.dofusbestiaire.db.FeedReaderContract
import com.example.dofusbestiaire.db.FeedReaderDbHelper
import com.example.dofusbestiaire.models.Monsters
import com.example.dofusbestiaire.ui.RecyclerViewCardCreator
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    lateinit var recyclerViewCardCreator: RecyclerViewCardCreator
    lateinit var adapter: ListViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        val dbHelper = FeedReaderDbHelper(this)
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID, "test")
        }
        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID)

// Filter results WHERE "title" = 'My Title'
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID} = ?"
        val selectionArgs = arrayOf("My Title")


        val cursor = db.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null         // The sort order
        )
        val itemIds = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID))
                itemIds.add(itemId)
            }
        }
        cursor.close()

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