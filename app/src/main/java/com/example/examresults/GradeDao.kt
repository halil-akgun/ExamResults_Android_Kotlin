package com.example.examresults

import android.content.ContentValues

class GradeDao {

    fun addGrade(dbHelper: DBHelper, name: String, score1: Int, score2: Int) {
        val db = dbHelper.writableDatabase
        db.insert("scores", null, ContentValues().apply {
            put("name", name)
            put("score1", score1)
            put("score2", score2)
        })
        db.close()
    }

    fun updateGrade(dbHelper: DBHelper, id: Int, name: String, score1: Int, score2: Int) {
        val db = dbHelper.writableDatabase
        db.update("scores", ContentValues().apply {
            put("name", name)
            put("score1", score1)
            put("score2", score2)
        }, "id = ?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteGrade(dbHelper: DBHelper, id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("scores", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    fun getAllGrades(dbHelper: DBHelper): List<Grade> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM scores", null)
        val gradeList = ArrayList<Grade>()
        while (cursor.moveToNext()) {
            val grade = Grade(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("name")),
                cursor.getInt(cursor.getColumnIndexOrThrow("score1")),
                cursor.getInt(cursor.getColumnIndexOrThrow("score2"))
            )
            gradeList.add(grade)
        }
        cursor.close()
        db.close()
        return gradeList
    }
}