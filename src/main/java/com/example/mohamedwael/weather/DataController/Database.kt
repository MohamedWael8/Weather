package com.example.mohamedwael.weather.DataController

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


internal class DBHelper (val context: Context , val DATABASE_NAME : String): SQLiteOpenHelper(context, DATABASE_NAME , null, 1)
{
    val DATABASE_CREATE= "create table MyEmployees( _id integer primary key,name text not null);";

    override fun onCreate(db: SQLiteDatabase)
    {
        db.execSQL(DATABASE_CREATE);
    }
    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {

    }
}