package com.example.dofusbestiaire.data

import android.content.ContentValues
import android.provider.BaseColumns
import com.example.dofusbestiaire.db.FeedReaderContract
import com.example.dofusbestiaire.db.FeedReaderDbHelper

class FavoritesMonsterClient: IFavoritesMonsterClient {
}



//val dbHelper = FeedReaderDbHelper(this)
//val db = dbHelper.writableDatabase
//val values = ContentValues().apply {
//    put(FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID, "test")
//}
//val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
//
//// Define a projection that specifies which columns from the database
//// you will actually use after this query.
//val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID)
//
//// Filter results WHERE "title" = 'My Title'
//val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID} = ?"
//val selectionArgs = arrayOf("My Title")
//
//
//val cursor = db.query(
//    FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
//    projection,             // The array of columns to return (pass null to get all)
//    null,              // The columns for the WHERE clause
//    null,          // The values for the WHERE clause
//    null,                   // don't group the rows
//    null,                   // don't filter by row groups
//    null         // The sort order
//)
//val itemIds = mutableListOf<String>()
//with(cursor) {
//    while (moveToNext()) {
//        val itemId = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID))
//        itemIds.add(itemId)
//    }
//}
//cursor.close()