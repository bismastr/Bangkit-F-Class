package com.muhammadfurqan.bangkitfclass.sqlite.db

import android.content.ContentValues
import android.content.Context
import com.muhammadfurqan.bangkitfclass.sqlite.BookModel

/**
 * @author by furqan on 09/04/2021
 */
class BookDatabaseManager(context: Context) {

    private val db by lazy {
        BookDatabaseOpenHelper(context)
    }

    fun saveData(name: String) {
        // get writeable database
        val writeableDb = db.writableDatabase

        // set the data to content values
        val contentValue = ContentValues()
        contentValue.put(BookDatabaseOpenHelper.KEY_NAME, name)

        // insert the data to database
        writeableDb.insert(
            BookDatabaseOpenHelper.TABLE_BOOK,
            null,
            contentValue
        )

        // close the database
        writeableDb.close()
    }

    fun updateData(name: String, id: String?) {
        val writeableDb = db.writableDatabase

        val contentValue = ContentValues()
        contentValue.put(BookDatabaseOpenHelper.KEY_NAME, name)

        val selection = BookDatabaseOpenHelper.KEY_ID + " = ?"

        writeableDb.update(
            BookDatabaseOpenHelper.TABLE_BOOK,
            contentValue,
            selection,
            arrayOf(id)
        )

        writeableDb.close()
    }

    fun delete(id: String?) {
        val writeableDb = db.writableDatabase
        val selection = BookDatabaseOpenHelper.KEY_ID + " = ?"

        val selectionArgs = arrayOf(id)

        writeableDb.delete(
            BookDatabaseOpenHelper.TABLE_BOOK,
            selection,
            selectionArgs
        )

        writeableDb.close()
    }

    fun getData(): List<BookModel> {
        val arrayBookList = arrayListOf<BookModel>()

        val bookList: MutableList<BookModel> = mutableListOf()

        // get readable database
        val readableDb = db.readableDatabase

        // get data from database
        val cursor = readableDb.rawQuery(
            "SELECT * FROM ${BookDatabaseOpenHelper.TABLE_BOOK}",
            null
        )

        // check the cursor null
        cursor?.apply {
            // do a loop until the end of the data
            while (moveToNext()) {
                // get the id and name from this row
                val book = BookModel(
                    id = getInt(getColumnIndexOrThrow(BookDatabaseOpenHelper.KEY_ID)),
                    name = getString(getColumnIndexOrThrow(BookDatabaseOpenHelper.KEY_NAME))
                )
                // add to book list
                arrayBookList.add(book)
                bookList.add(book)
            }
        }

        // return the book list
        return bookList
    }

}