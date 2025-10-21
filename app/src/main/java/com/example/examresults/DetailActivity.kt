package com.example.examresults

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examresults.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var score: Grade
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbarDetail.title = "Detail"
        setSupportActionBar(binding.toolbarDetail)

        dbHelper = DBHelper(this)

        score = intent.getSerializableExtra("grade") as Grade
        binding.editTextLessonDetail.setText(score.name)
        binding.editTextScore1Detail.setText(score.score1.toString())
        binding.editTextScore2Detail.setText(score.score2.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                Snackbar.make(binding.toolbarDetail, "Are you sure?", Snackbar.LENGTH_SHORT)
                    .setAction("Yes") {
                        GradeDao().deleteGrade(dbHelper, score.id)
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    .show()

                return true
            }

            R.id.action_edit -> {


                val name = binding.editTextLessonDetail.text.toString().trim()
                val score1 = binding.editTextScore1Detail.text.toString()
                val score2 = binding.editTextScore2Detail.text.toString()

                if (TextUtils.isEmpty(name)) {
                    Snackbar.make(binding.toolbarDetail, "Lesson name cannot be empty", Snackbar.LENGTH_SHORT).show()
                    return false
                } else if (TextUtils.isEmpty(score1) || score1.toInt() < 0 || score1.toInt() > 100) {
                    Snackbar.make(binding.toolbarDetail, "Note 1 must be between 0 and 100", Snackbar.LENGTH_SHORT).show()
                    return false
                } else if (TextUtils.isEmpty(score2) || score2.toInt() < 0 || score2.toInt() > 100) {
                    Snackbar.make(binding.toolbarDetail, "Note 2 must be between 0 and 100", Snackbar.LENGTH_SHORT).show()
                    return false
                }

                GradeDao().updateGrade(dbHelper, score.id, name, score1.toInt(), score2.toInt())

                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return true
            }

            else -> return false
        }
    }
}