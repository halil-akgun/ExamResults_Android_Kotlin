package com.example.examresults

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examresults.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var scoreList: ArrayList<Grade>
    private lateinit var adaptor: GradeAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.title = "Exam Results"
        binding.toolbar.subtitle = "Average: 50%"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        scoreList = ArrayList()
        scoreList.add(Grade(1, "Math", 50, 100))
        scoreList.add(Grade(2, "English", 60, 90))
        scoreList.add(Grade(3, "Physics", 50, 60))
        scoreList.add(Grade(4, "Chemistry", 30, 40))
        scoreList.add(Grade(5, "Biology", 40, 45))

        adaptor = GradeAdaptor(this, scoreList)
        binding.rv.adapter = adaptor

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, SaveNoteActivity::class.java)
            startActivity(intent)
        }
    }
}