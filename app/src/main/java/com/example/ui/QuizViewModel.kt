package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.QuizHistoryEntity
import com.example.data.model.AnswerSubmission
import com.example.data.model.DifficultyLevel
import com.example.data.model.QuizCategory
import com.example.data.model.QuizQuestion
import com.example.data.model.TimerConfig
import com.example.data.model.TimerMode
import com.example.data.repository.QuizQuestionBank
import com.example.data.repository.QuizRepository
import com.example.ui.util.SoundAndHapticsHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    QUIZ_CONFIG,
    ACTIVE_QUIZ,
    QUIZ_RESULT,
    BOOKMARKS,
    MISTAKES_REVIEW,
    FLIPBOOK_NOTES,
    SELF_TEST
}

data class QuizSessionState(
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val timeRemainingSeconds: Int = 30,
    val totalAllocatedSeconds: Int = 30,
    val isTimerActive: Boolean = false,
    val submissions: Map<Int, AnswerSubmission> = emptyMap(),
    val isCompleted: Boolean = false,
    val totalTimeSpentSeconds: Int = 0
)

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = QuizRepository(AppDatabase.getInstance(application))
    val soundHelper = SoundAndHapticsHelper(application)

    // Current Screen
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    // Config state
    private val _selectedDifficulty = MutableStateFlow<DifficultyLevel?>(null) // null = all
    val selectedDifficulty: StateFlow<DifficultyLevel?> = _selectedDifficulty.asStateFlow()

    private val _selectedCategory = MutableStateFlow(QuizCategory.ALL)
    val selectedCategory: StateFlow<QuizCategory> = _selectedCategory.asStateFlow()

    private val _questionCount = MutableStateFlow(15)
    val questionCount: StateFlow<Int> = _questionCount.asStateFlow()

    private val _timerConfig = MutableStateFlow(TimerConfig())
    val timerConfig: StateFlow<TimerConfig> = _timerConfig.asStateFlow()

    // Active session state
    private val _session = MutableStateFlow(QuizSessionState())
    val session: StateFlow<QuizSessionState> = _session.asStateFlow()

    // History & Bookmarks from Room
    val quizHistory: StateFlow<List<QuizHistoryEntity>> = repository.getAllHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedQuestions: StateFlow<List<QuizQuestion>> = repository.getBookmarkedQuestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val mistakeQuestions: StateFlow<List<QuizQuestion>> = repository.getMistakeQuestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private var timerJob: Job? = null

    fun navigateTo(screen: AppScreen) {
        if (screen != AppScreen.ACTIVE_QUIZ) {
            pauseTimer()
        }
        _currentScreen.value = screen
    }

    fun setDifficulty(difficulty: DifficultyLevel?) {
        _selectedDifficulty.value = difficulty
        // Update recommended seconds if set
        if (difficulty != null && _timerConfig.value.mode == TimerMode.PER_QUESTION) {
            _timerConfig.value = _timerConfig.value.copy(
                secondsPerQuestion = difficulty.recommendedSeconds
            )
        }
    }

    fun setCategory(category: QuizCategory) {
        _selectedCategory.value = category
    }

    fun setQuestionCount(count: Int) {
        _questionCount.value = count
    }

    fun updateTimerConfig(newConfig: TimerConfig) {
        _timerConfig.value = newConfig
    }

    fun toggleSound() {
        _timerConfig.value = _timerConfig.value.copy(
            isSoundEnabled = !_timerConfig.value.isSoundEnabled
        )
    }

    fun startQuiz(
        difficulty: DifficultyLevel? = _selectedDifficulty.value,
        category: QuizCategory = _selectedCategory.value,
        customQuestionList: List<QuizQuestion>? = null
    ) {
        _selectedDifficulty.value = difficulty
        _selectedCategory.value = category

        val questions = customQuestionList ?: QuizQuestionBank.getRandomSample(
            count = _questionCount.value,
            difficulty = difficulty,
            category = category
        )

        if (questions.isEmpty()) return

        val initialSeconds = when (_timerConfig.value.mode) {
            TimerMode.PER_QUESTION -> _timerConfig.value.secondsPerQuestion
            TimerMode.TOTAL_EXAM -> _timerConfig.value.totalExamMinutes * 60
            TimerMode.UNTIMED -> 0
        }

        _session.value = QuizSessionState(
            questions = questions,
            currentIndex = 0,
            selectedOptionIndex = null,
            isAnswerSubmitted = false,
            timeRemainingSeconds = initialSeconds,
            totalAllocatedSeconds = initialSeconds,
            isTimerActive = _timerConfig.value.mode != TimerMode.UNTIMED,
            submissions = emptyMap(),
            isCompleted = false,
            totalTimeSpentSeconds = 0
        )

        _currentScreen.value = AppScreen.ACTIVE_QUIZ
        startTimer()
    }

    fun startMistakesPractice() {
        val mistakes = mistakeQuestions.value
        if (mistakes.isNotEmpty()) {
            startQuiz(
                difficulty = null,
                category = QuizCategory.ALL,
                customQuestionList = mistakes.shuffled()
            )
        }
    }

    fun startBookmarksPractice() {
        val bookmarks = bookmarkedQuestions.value
        if (bookmarks.isNotEmpty()) {
            startQuiz(
                difficulty = null,
                category = QuizCategory.ALL,
                customQuestionList = bookmarks.shuffled()
            )
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        if (_timerConfig.value.mode == TimerMode.UNTIMED) return

        timerJob = viewModelScope.launch {
            while (_session.value.timeRemainingSeconds > 0 && !_session.value.isAnswerSubmitted && !_session.value.isCompleted) {
                delay(1000L)
                val remaining = _session.value.timeRemainingSeconds - 1
                val totalSpent = _session.value.totalTimeSpentSeconds + 1

                _session.value = _session.value.copy(
                    timeRemainingSeconds = remaining,
                    totalTimeSpentSeconds = totalSpent
                )

                // Sound & Haptic alerts in final seconds
                if (remaining in 1..5) {
                    soundHelper.playUrgentTickTone(_timerConfig.value.isSoundEnabled)
                    soundHelper.vibrateShort(_timerConfig.value.isHapticsEnabled)
                } else if (remaining in 6..10 && remaining % 2 == 0) {
                    soundHelper.playTickTone(_timerConfig.value.isSoundEnabled)
                }

                // Timeout
                if (remaining <= 0) {
                    handleTimeout()
                    break
                }
            }
        }
    }

    private fun pauseTimer() {
        timerJob?.cancel()
        _session.value = _session.value.copy(isTimerActive = false)
    }

    fun selectOption(index: Int) {
        val currentSession = _session.value
        if (currentSession.isAnswerSubmitted || currentSession.isCompleted) return

        _session.value = currentSession.copy(selectedOptionIndex = index)
    }

    fun submitAnswer() {
        val currentSession = _session.value
        if (currentSession.isAnswerSubmitted || currentSession.isCompleted) return

        timerJob?.cancel()

        val question = currentSession.questions.getOrNull(currentSession.currentIndex) ?: return
        val selected = currentSession.selectedOptionIndex
        val isCorrect = selected == question.correctIndex

        val timeSpentOnThisQuestion = currentSession.totalAllocatedSeconds - currentSession.timeRemainingSeconds

        val submission = AnswerSubmission(
            questionId = question.id,
            selectedIndex = selected,
            isCorrect = isCorrect,
            timeSpentSeconds = maxOf(1, timeSpentOnThisQuestion)
        )

        val updatedSubmissions = currentSession.submissions + (currentSession.currentIndex to submission)

        // Audio & Haptic feedback
        if (isCorrect) {
            soundHelper.playCorrectTone(_timerConfig.value.isSoundEnabled)
            soundHelper.vibrateShort(_timerConfig.value.isHapticsEnabled)
            // If answered correctly, remove from mistake records if it was there
            viewModelScope.launch {
                repository.removeMistake(question.id)
            }
        } else {
            soundHelper.playIncorrectTone(_timerConfig.value.isSoundEnabled)
            soundHelper.vibrateWarning(_timerConfig.value.isHapticsEnabled)
            // Record mistake for review practice
            viewModelScope.launch {
                repository.recordMistake(question.id, selected ?: -1)
            }
        }

        _session.value = currentSession.copy(
            isAnswerSubmitted = true,
            submissions = updatedSubmissions
        )

        // If Instant Feedback is turned OFF, automatically jump to next or complete
        if (!_timerConfig.value.instantExplanation) {
            goToNextQuestion()
        }
    }

    private fun handleTimeout() {
        val currentSession = _session.value
        if (currentSession.isAnswerSubmitted || currentSession.isCompleted) return

        val question = currentSession.questions.getOrNull(currentSession.currentIndex) ?: return

        soundHelper.playIncorrectTone(_timerConfig.value.isSoundEnabled)
        soundHelper.vibrateWarning(_timerConfig.value.isHapticsEnabled)

        val submission = AnswerSubmission(
            questionId = question.id,
            selectedIndex = null, // Timeout
            isCorrect = false,
            timeSpentSeconds = currentSession.totalAllocatedSeconds
        )

        val updatedSubmissions = currentSession.submissions + (currentSession.currentIndex to submission)

        viewModelScope.launch {
            repository.recordMistake(question.id, -1)
        }

        _session.value = currentSession.copy(
            isAnswerSubmitted = true,
            submissions = updatedSubmissions
        )

        if (!_timerConfig.value.instantExplanation) {
            goToNextQuestion()
        }
    }

    fun goToNextQuestion() {
        val currentSession = _session.value
        val nextIndex = currentSession.currentIndex + 1

        if (nextIndex >= currentSession.questions.size) {
            finishQuiz()
        } else {
            val nextAllocatedSeconds = when (_timerConfig.value.mode) {
                TimerMode.PER_QUESTION -> _timerConfig.value.secondsPerQuestion
                TimerMode.TOTAL_EXAM -> currentSession.timeRemainingSeconds // continue exam timer
                TimerMode.UNTIMED -> 0
            }

            _session.value = currentSession.copy(
                currentIndex = nextIndex,
                selectedOptionIndex = null,
                isAnswerSubmitted = false,
                timeRemainingSeconds = nextAllocatedSeconds,
                totalAllocatedSeconds = nextAllocatedSeconds,
                isTimerActive = _timerConfig.value.mode != TimerMode.UNTIMED
            )

            startTimer()
        }
    }

    private fun finishQuiz() {
        timerJob?.cancel()
        val currentSession = _session.value
        val score = currentSession.submissions.values.count { it.isCorrect }
        val total = currentSession.questions.size

        val difficultyLabel = _selectedDifficulty.value?.title ?: "All Levels"
        val categoryLabel = _selectedCategory.value.displayName

        viewModelScope.launch {
            repository.saveQuizHistory(
                score = score,
                totalQuestions = total,
                difficulty = difficultyLabel,
                category = categoryLabel,
                timeSpentSeconds = currentSession.totalTimeSpentSeconds
            )
        }

        _session.value = currentSession.copy(
            isCompleted = true,
            isTimerActive = false
        )
        _currentScreen.value = AppScreen.QUIZ_RESULT
    }

    fun toggleBookmarkCurrentQuestion() {
        val question = _session.value.questions.getOrNull(_session.value.currentIndex) ?: return
        val isBookmarkedNow = bookmarkedQuestions.value.any { it.id == question.id }
        viewModelScope.launch {
            repository.toggleBookmark(question.id, isBookmarkedNow)
        }
    }

    fun toggleBookmark(questionId: String) {
        val isBookmarkedNow = bookmarkedQuestions.value.any { it.id == questionId }
        viewModelScope.launch {
            repository.toggleBookmark(questionId, isBookmarkedNow)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }

    fun clearMistakes() {
        viewModelScope.launch {
            repository.clearAllMistakes()
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        soundHelper.release()
    }
}
