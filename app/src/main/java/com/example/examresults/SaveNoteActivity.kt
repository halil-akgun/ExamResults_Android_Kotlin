package com.example.examresults

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examresults.databinding.ActivitySaveNoteBinding

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
            val score1 = binding.editTextNote1.text.toString().toInt()
            val score2 = binding.editTextNote2.text.toString().toInt()

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Lesson name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (score1 < 0 || score1 > 100) {
                Toast.makeText(this, "Note 1 must be between 0 and 100", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (score2 < 0 || score2 > 100) {
                Toast.makeText(this, "Note 2 must be between 0 and 100", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            GradeDao().addGrade(dbHelper, name, score1, score2)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // remove this activity from the stack
        }
    }
}