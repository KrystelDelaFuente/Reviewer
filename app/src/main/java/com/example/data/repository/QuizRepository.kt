package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.QuestionBookmarkEntity
import com.example.data.local.QuizHistoryEntity
import com.example.data.local.MistakeRecordEntity
import com.example.data.model.DifficultyLevel
import com.example.data.model.QuizCategory
import com.example.data.model.QuizQuestion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizRepository(private val database: AppDatabase) {

    private val dao = database.quizDao()

    fun getAllHistory(): Flow<List<QuizHistoryEntity>> = dao.getAllHistory()

    suspend fun saveQuizHistory(
        score: Int,
        totalQuestions: Int,
        difficulty: String,
        category: String,
        timeSpentSeconds: Int
    ): Long {
        val percentage = if (totalQuestions > 0) (score * 100) / totalQuestions else 0
        val entity = QuizHistoryEntity(
            score = score,
            totalQuestions = totalQuestions,
            percentage = percentage,
            difficulty = difficulty,
            category = category,
            timeSpentSeconds = timeSpentSeconds
        )
        return dao.insertHistory(entity)
    }

    suspend fun clearHistory() = dao.clearHistory()

    fun getBookmarkedQuestions(): Flow<List<QuizQuestion>> {
        return dao.getAllBookmarks().map { bookmarks ->
            val set = bookmarks.map { it.questionId }.toSet()
            QuizQuestionBank.allQuestions.filter { set.contains(it.id) }
        }
    }

    fun isBookmarked(questionId: String): Flow<Boolean> = dao.isBookmarked(questionId)

    suspend fun toggleBookmark(questionId: String, isCurrentlyBookmarked: Boolean) {
        if (isCurrentlyBookmarked) {
            dao.deleteBookmark(questionId)
        } else {
            dao.insertBookmark(QuestionBookmarkEntity(questionId = questionId))
        }
    }

    fun getMistakeQuestions(): Flow<List<QuizQuestion>> {
        return dao.getAllMistakes().map { mistakes ->
            val set = mistakes.map { it.questionId }.toSet()
            QuizQuestionBank.allQuestions.filter { set.contains(it.id) }
        }
    }

    suspend fun recordMistake(questionId: String, selectedOptionIndex: Int) {
        dao.insertOrUpdateMistake(
            MistakeRecordEntity(
                questionId = questionId,
                lastSelectedOption = selectedOptionIndex
            )
        )
    }

    suspend fun removeMistake(questionId: String) {
        dao.clearMistake(questionId)
    }

    suspend fun clearAllMistakes() {
        dao.clearAllMistakes()
    }
}
