package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.TimerMode
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.DifficultyBadge
import com.example.ui.components.TimerCountdownRing
import com.example.ui.components.TimerProgressBar
import com.example.ui.theme.AnimatedFontFamily
import com.example.ui.theme.DifficultyAdvanced
import com.example.ui.theme.DifficultyBeginner
import com.example.ui.theme.FriendlyBodyFontFamily
import com.example.ui.theme.TimerCritical
import com.example.ui.theme.TimerSafe
import com.example.ui.theme.TimerWarning

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveQuizScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val session by viewModel.session.collectAsState()
    val timerConfig by viewModel.timerConfig.collectAsState()
    val bookmarks by viewModel.bookmarkedQuestions.collectAsState()

    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = {
                Text(
                    "Quit Active Quiz?",
                    fontFamily = AnimatedFontFamily,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    "Are you sure you want to exit? Your progress for this practice session will not be saved.",
                    fontFamily = FriendlyBodyFontFamily
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showExitDialog = false
                        viewModel.navigateTo(AppScreen.HOME)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("confirm_exit_button")
                ) {
                    Text("Exit", fontFamily = AnimatedFontFamily, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("Resume", fontFamily = AnimatedFontFamily, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    val currentQuestion = session.questions.getOrNull(session.currentIndex)

    if (currentQuestion == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No question available.", fontFamily = FriendlyBodyFontFamily)
        }
        return
    }

    val isCurrentBookmarked = bookmarks.any { it.id == currentQuestion.id }
    val isLastQuestion = session.currentIndex == session.questions.size - 1

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "Item ${session.currentIndex + 1}/${session.questions.size}",
                                fontFamily = AnimatedFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        DifficultyBadge(difficulty = currentQuestion.difficulty)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { showExitDialog = true },
                        modifier = Modifier.testTag("exit_quiz_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Exit Quiz"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.toggleBookmarkCurrentQuestion() },
                        modifier = Modifier.testTag("bookmark_toggle_button")
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (isCurrentBookmarked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (isCurrentBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                    contentDescription = "Bookmark Question",
                                    tint = if (isCurrentBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                    IconButton(
                        onClick = { viewModel.toggleSound() },
                        modifier = Modifier.testTag("quiz_sound_toggle")
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (timerConfig.isSoundEnabled) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (timerConfig.isSoundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                                    contentDescription = "Toggle Audio Ticks",
                                    tint = if (timerConfig.isSoundEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    if (!session.isAnswerSubmitted) {
                        Button(
                            onClick = { viewModel.submitAnswer() },
                            enabled = session.selectedOptionIndex != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("submit_answer_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "Submit Answer",
                                fontFamily = AnimatedFontFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Button(
                            onClick = { viewModel.goToNextQuestion() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("next_question_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isLastQuestion) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = if (isLastQuestion) "Finish & View Results" else "Next Question",
                                fontFamily = AnimatedFontFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = if (isLastQuestion) Icons.Default.DoneAll else Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("active_quiz_content"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Animated Timer Bar & Countdown
            if (timerConfig.mode != TimerMode.UNTIMED) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Timer,
                                        contentDescription = null,
                                        tint = when {
                                            session.timeRemainingSeconds <= 5 -> TimerCritical
                                            session.timeRemainingSeconds <= 10 -> TimerWarning
                                            else -> TimerSafe
                                        },
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (timerConfig.mode == TimerMode.PER_QUESTION) "Pacing Timer" else "Exam Timer",
                                        fontFamily = AnimatedFontFamily,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                if (session.timeRemainingSeconds <= 0 && session.isAnswerSubmitted) {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = TimerCritical.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = "TIME EXPIRED!",
                                            color = TimerCritical,
                                            fontFamily = AnimatedFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                } else {
                                    TimerCountdownRing(
                                        remainingSeconds = session.timeRemainingSeconds,
                                        totalSeconds = session.totalAllocatedSeconds
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            TimerProgressBar(
                                remainingSeconds = session.timeRemainingSeconds,
                                totalSeconds = session.totalAllocatedSeconds
                            )
                        }
                    }
                }
            }

            // Question Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Text(
                                    text = currentQuestion.category.displayName,
                                    fontFamily = AnimatedFontFamily,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(
                                    text = "Flipbook P. ${currentQuestion.pageReference}",
                                    fontFamily = AnimatedFontFamily,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = currentQuestion.questionText,
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 25.sp
                        )
                    }
                }
            }

            // Options Header
            item {
                Text(
                    text = "Tap your answer:",
                    fontFamily = AnimatedFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Bouncy, popping options
            items(currentQuestion.options.size) { index ->
                val optionText = currentQuestion.options[index]
                val optionLetter = ('A' + index).toString()
                val isSelected = session.selectedOptionIndex == index
                val isCorrect = index == currentQuestion.correctIndex

                val (cardColor, borderColor, contentColor) = when {
                    session.isAnswerSubmitted -> {
                        when {
                            isCorrect -> Triple(
                                DifficultyBeginner.copy(alpha = 0.15f),
                                DifficultyBeginner,
                                DifficultyBeginner
                            )
                            isSelected -> Triple(
                                DifficultyAdvanced.copy(alpha = 0.15f),
                                DifficultyAdvanced,
                                DifficultyAdvanced
                            )
                            else -> Triple(
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                    }
                    isSelected -> Triple(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    else -> Triple(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.outlineVariant,
                        MaterialTheme.colorScheme.onSurface
                    )
                }

                val scaleAnim by animateFloatAsState(
                    targetValue = if (isSelected) 1.01f else 1.0f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "option_scale"
                )

                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleAnim)
                        .clickable(enabled = !session.isAnswerSubmitted) {
                            viewModel.selectOption(index)
                        }
                        .testTag("option_card_$index"),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = cardColor),
                    border = BorderStroke(if (isSelected || (session.isAnswerSubmitted && isCorrect)) 2.dp else 1.dp, borderColor)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Letter Badge
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    if (session.isAnswerSubmitted && isCorrect) DifficultyBeginner
                                    else if (session.isAnswerSubmitted && isSelected) DifficultyAdvanced
                                    else if (isSelected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.surfaceVariant
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (session.isAnswerSubmitted && isCorrect) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            } else if (session.isAnswerSubmitted && isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            } else {
                                Text(
                                    text = optionLetter,
                                    fontFamily = AnimatedFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 15.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = optionText,
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 15.sp,
                            fontWeight = if (isSelected || (session.isAnswerSubmitted && isCorrect)) FontWeight.Bold else FontWeight.Medium,
                            color = contentColor,
                            lineHeight = 22.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Explanation & Legal Rationale Card
            if (session.isAnswerSubmitted && timerConfig.instantExplanation) {
                item {
                    val submission = session.submissions[session.currentIndex]
                    val isCorrect = submission?.isCorrect == true
                    val isTimeout = submission?.selectedIndex == null

                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically()
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("explanation_card"),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isCorrect) DifficultyBeginner.copy(alpha = 0.12f)
                                else DifficultyAdvanced.copy(alpha = 0.12f)
                            ),
                            border = BorderStroke(
                                1.5.dp,
                                if (isCorrect) DifficultyBeginner else DifficultyAdvanced
                            )
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.ErrorOutline,
                                        contentDescription = null,
                                        tint = if (isCorrect) DifficultyBeginner else DifficultyAdvanced,
                                        modifier = Modifier.size(22.dp)
                                    )
                                    Text(
                                        text = when {
                                            isCorrect -> "Correct! Great Knowledge"
                                            isTimeout -> "Time's Up!"
                                            else -> "Needs Review"
                                        },
                                        fontFamily = AnimatedFontFamily,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCorrect) DifficultyBeginner else DifficultyAdvanced
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.surface
                                ) {
                                    Text(
                                        text = "Legal Citation: ${currentQuestion.legalBasis}",
                                        fontFamily = AnimatedFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = currentQuestion.explanation,
                                    fontFamily = FriendlyBodyFontFamily,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    lineHeight = 21.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
