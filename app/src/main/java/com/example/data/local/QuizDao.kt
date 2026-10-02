package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    // History
    @Query("SELECT * FROM quiz_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<QuizHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: QuizHistoryEntity): Long

    @Query("DELETE FROM quiz_history")
    suspend fun clearHistory()

    // Bookmarks
    @Query("SELECT * FROM question_bookmarks ORDER BY savedAt DESC")
    fun getAllBookmarks(): Flow<List<QuestionBookmarkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM question_bookmarks WHERE questionId = :questionId)")
    fun isBookmarked(questionId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: QuestionBookmarkEntity)

    @Query("DELETE FROM question_bookmarks WHERE questionId = :questionId")
    suspend fun deleteBookmark(questionId: String)

    // Mistakes
    @Query("SELECT * FROM mistake_records ORDER BY incorrectCount DESC, lastAttemptAt DESC")
    fun getAllMistakes(): Flow<List<MistakeRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMistake(mistake: MistakeRecordEntity)

    @Query("DELETE FROM mistake_records WHERE questionId = :questionId")
    suspend fun clearMistake(questionId: String)

    @Query("DELETE FROM mistake_records")
    suspend fun clearAllMistakes()
}
