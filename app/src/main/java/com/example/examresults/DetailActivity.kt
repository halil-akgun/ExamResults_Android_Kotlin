package com.example.examresults

import android.content.Intent
import android.os.Bundle
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

        val grade = intent.getSerializableExtra("grade") as Grade
        binding.editTextLessonDetail.setText(grade.name)
        binding.editTextScore1Detail.setText(grade.score1.toString())
        binding.editTextScore2Detail.setText(grade.score2.toString())
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
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    .show()

                return true
            }

            R.id.action_edit -> {
                startActivity(Intent(this, SaveNoteActivity::class.java))
                finish()
                return true
            }

            else -> return false
        }
    }
}