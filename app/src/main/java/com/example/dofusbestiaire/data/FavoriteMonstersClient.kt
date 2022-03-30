package com.example.dofusbestiaire.data

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.dofusbestiaire.db.FeedReaderContract
import com.example.dofusbestiaire.db.FeedReaderDbHelper

class FavoriteMonstersClient(context: Context) : IFavoriteMonstersClient {
    private val dbHelper = FeedReaderDbHelper(context)
    private val db = dbHelper.readableDatabase

    override fun saveFavoriteMonster(monsterId: Int) {
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID, monsterId)
        }
        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
    }

    override fun getFavoriteMonsters(): MutableList<String> {
        val projection =
            arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID)

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
                val itemId =
                    getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID))
                itemIds.add(itemId)
            }
        }
        cursor.close()
        return itemIds
    }

    override fun deleteFavoriteMonster(monsterId: Int): Int {
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID} LIKE $monsterId"

        val deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, null)
        return deletedRows
    }
}
