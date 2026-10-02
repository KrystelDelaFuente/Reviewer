package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.screens.ActiveQuizScreen
import com.example.ui.screens.FlipbookNotesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuizConfigScreen
import com.example.ui.screens.QuizResultScreen
import com.example.ui.screens.ReviewMistakesAndBookmarksScreen
import com.example.ui.screens.SelfTestScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DarReviewerApp(viewModel = quizViewModel)
                }
            }
        }
    }
}

@Composable
fun DarReviewerApp(viewModel: QuizViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()

    when (currentScreen) {
        AppScreen.HOME -> HomeScreen(viewModel = viewModel)
        AppScreen.QUIZ_CONFIG -> QuizConfigScreen(viewModel = viewModel)
        AppScreen.ACTIVE_QUIZ -> ActiveQuizScreen(viewModel = viewModel)
        AppScreen.QUIZ_RESULT -> QuizResultScreen(viewModel = viewModel)
        AppScreen.FLIPBOOK_NOTES -> FlipbookNotesScreen(viewModel = viewModel)
        AppScreen.SELF_TEST -> SelfTestScreen(viewModel = viewModel)
        AppScreen.MISTAKES_REVIEW -> ReviewMistakesAndBookmarksScreen(viewModel = viewModel, isMistakesMode = true)
        AppScreen.BOOKMARKS -> ReviewMistakesAndBookmarksScreen(viewModel = viewModel, isMistakesMode = false)
    }
}
