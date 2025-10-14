package com.example.examresults

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "scores.sqlite", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE scores (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score1 INTEGER, score2 INTEGER)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS scores")
        onCreate(db)
    }
}