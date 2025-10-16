package com.example.examresults

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
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
    private lateinit var dbHelper: DBHelper

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
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        scoreList = ArrayList()

//        scoreList.add(Grade(1, "Math", 50, 100))
//        scoreList.add(Grade(2, "English", 60, 90))
//        scoreList.add(Grade(3, "Physics", 50, 60))
//        scoreList.add(Grade(4, "Chemistry", 30, 40))
//        scoreList.add(Grade(5, "Biology", 40, 45))

        dbHelper = DBHelper(this)
        scoreList = GradeDao().getAllGrades(dbHelper)

        var totalScore = 0
        var count = 0
        for (grade in scoreList) {
            totalScore += (grade.score1 + grade.score2) / 2
            count++
        }
        binding.toolbar.subtitle = "Average: ${totalScore / count}%"

        adaptor = GradeAdaptor(this, scoreList)
        binding.rv.adapter = adaptor

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, SaveNoteActivity::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}