package com.example.examresults

import java.io.Serializable

data class Grade(var id: Int, var name: String, var score1: Int, var score2: Int): Serializable {
}