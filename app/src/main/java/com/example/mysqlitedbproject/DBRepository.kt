package com.example.mysqlitedbproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBRepository(private val context: Context) {
    private val DB_NAME:String="Lerner"
    private val TABLE_NAME: String ="Notes"
    private val DB_VERSION: Int = 1
    private val SR_NO: String = "SR_NO"
    private val TITLE: String= "Title"
    private val DESCRYP: String= "Description"
    private val CREATED_AT: String= "CreatedAt_ModifiedAt"

    private val CREATE_TABLE= buildString {
        append("CREATE TABLE ")
        append(TABLE_NAME)
        append(" (")
        append(SR_NO)
        append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
        append(TITLE)
        append(" TEXT, ")
        append(DESCRYP)
        append(" TEXT, ")
        append(CREATED_AT)
        append(" TEXT)")

    }

    private var myDB:MyDB=MyDB(context)
    private var sqLiteDatabase:SQLiteDatabase= myDB.writableDatabase

    fun saveRecords(title:String, descrip:String, time:String){
        val contentValues = ContentValues()
        contentValues.put(TITLE, title)
        contentValues.put(DESCRYP, descrip)
        contentValues.put(CREATED_AT, time)

        // TODO: Putting a check alongwith saving data to the table
        val id:Long = sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
        if(id.equals("-1")){
            Toast.makeText(context, "something Went Wrong", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully Saved", Toast.LENGTH_SHORT).show()
        }
    }

    inner class MyDB(context: Context):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION ){
        override fun onCreate(sqlDB: SQLiteDatabase?) {
            //sqlDB!!.execSQL(CREATE_TABLE)
            sqlDB?.let {
                it.execSQL(CREATE_TABLE)
            }
        }

        override fun onUpgrade(sqlDB: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }
    }
}