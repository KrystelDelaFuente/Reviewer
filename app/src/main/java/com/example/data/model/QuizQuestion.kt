package com.example.data.model

data class QuizQuestion(
    val id: String,
    val questionText: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val legalBasis: String,
    val category: QuizCategory,
    val difficulty: DifficultyLevel,
    val pageReference: Int
)

enum class TimerMode(val displayName: String) {
    PER_QUESTION("Per Question Countdown"),
    TOTAL_EXAM("Total Exam Timer"),
    UNTIMED("Untimed Practice")
}

data class TimerConfig(
    val mode: TimerMode = TimerMode.PER_QUESTION,
    val secondsPerQuestion: Int = 30, // 15, 30, 45, 60
    val totalExamMinutes: Int = 15,   // 10, 15, 20, 30
    val isSoundEnabled: Boolean = true,
    val isHapticsEnabled: Boolean = true,
    val instantExplanation: Boolean = true
)

data class AnswerSubmission(
    val questionId: String,
    val selectedIndex: Int?, // null if timed out
    val isCorrect: Boolean,
    val timeSpentSeconds: Int
)
