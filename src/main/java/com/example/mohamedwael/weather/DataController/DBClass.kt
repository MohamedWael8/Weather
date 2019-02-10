package com.example.mohamedwael.weather.DataController

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.database.Cursor
import android.util.Log


public class myDB(context: Context) {
    private var dbHelper: DBHelper? = null

    private var database: SQLiteDatabase? = null

    val EMP_TABLE = "MyEmployees"

    val EMP_ID = "_id"
    val EMP_NAME = "name"

    init {
        val dbHelper = DBHelper(context, "sup")
        database = dbHelper.getWritableDatabase();
        Log.i("AhoDatabase", "Ran init")
    }

    fun createRecords(id: String, name: String) {
        val values = ContentValues()
        values.put(EMP_ID, id)
        values.put(EMP_NAME, name)
        //Log.i("AhoDatabase", "Record Created")
        database?.rawQuery("insert into MyEmployees values ('lol','lol1')",null)
        val mCursor = database?.rawQuery("select * from MyEmployees", null)
        Log.i("AhoDatabase", mCursor?.count.toString())
    }

    fun selectRecords(): ArrayList<String>? {
        //val cols = arrayOf(EMP_ID, EMP_NAME)
        //Log.i("AhoDatabase", "Entered Select Records")
        database = dbHelper?.getReadableDatabase()
        val mCursor = database?.rawQuery("select * from MyEmployees", null)
        //Log.i("AhoDatabase", "Returned Cursor")
        mCursor?.moveToFirst();

        val array_list = ArrayList<String>()


        while(mCursor?.isAfterLast() == false){
            array_list.add(mCursor?.getString(mCursor.getColumnIndex(EMP_NAME)));
            mCursor?.moveToNext();
        }
        return array_list;
        //mCursor?.moveToFirst()
    }
}
