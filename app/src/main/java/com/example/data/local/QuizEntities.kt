package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_history")
data class QuizHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val difficulty: String,
    val category: String,
    val timeSpentSeconds: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "question_bookmarks")
data class QuestionBookmarkEntity(
    @PrimaryKey
    val questionId: String,
    val savedAt: Long = System.currentTimeMillis(),
    val userNote: String = ""
)

@Entity(tableName = "mistake_records")
data class MistakeRecordEntity(
    @PrimaryKey
    val questionId: String,
    val incorrectCount: Int = 1,
    val lastSelectedOption: Int,
    val lastAttemptAt: Long = System.currentTimeMillis()
)
