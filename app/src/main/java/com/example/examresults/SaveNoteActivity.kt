package com.example.examresults

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examresults.databinding.ActivitySaveNoteBinding
import com.google.android.material.snackbar.Snackbar

class SaveNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveNoteBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySaveNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DBHelper(this)

        binding.toolbarSaveNote.title = "Save Note"
        setSupportActionBar(binding.toolbarSaveNote)

        binding.buttonSaveNote.setOnClickListener {

            val name = binding.editTextLesson.text.toString().trim()
            val score1 = binding.editTextNote1.text.toString()
            val score2 = binding.editTextNote2.text.toString()

            if (TextUtils.isEmpty(name)) {
                Snackbar.make(binding.main, "Lesson name cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(score1)) {
                Snackbar.make(binding.main, "Note 1 must be between 0 and 100", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(score2)) {
                Snackbar.make(binding.main, "Note 2 must be between 0 and 100", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            GradeDao().addGrade(dbHelper, name, score1.toInt(), score2.toInt())

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // remove this activity from the stack
        }
    }
}