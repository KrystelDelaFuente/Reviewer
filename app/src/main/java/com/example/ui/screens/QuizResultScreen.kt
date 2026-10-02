package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.DifficultyBadge
import com.example.ui.theme.AnimatedFontFamily
import com.example.ui.theme.DifficultyAdvanced
import com.example.ui.theme.DifficultyBeginner
import com.example.ui.theme.DifficultyIntermediate
import com.example.ui.theme.FriendlyBodyFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizResultScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val session by viewModel.session.collectAsState()
    val selectedDifficulty by viewModel.selectedDifficulty.collectAsState()

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    val total = session.questions.size
    val correctCount = session.submissions.values.count { it.isCorrect }
    val incorrectCount = total - correctCount
    val percentage = if (total > 0) (correctCount * 100) / total else 0

    val (badgeText, badgeColor) = when {
        percentage >= 80 -> "🌟 EXCELLENT / EXAM READY" to DifficultyBeginner
        percentage >= 70 -> "👍 PASSED / SOLID RECALL" to DifficultyIntermediate
        else -> "📖 REVIEW RECOMMENDED" to DifficultyAdvanced
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Exam Score Card",
                        fontFamily = AnimatedFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { viewModel.navigateTo(AppScreen.HOME) },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .testTag("result_home_button"),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Home", fontFamily = AnimatedFontFamily, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {
                            viewModel.startQuiz(difficulty = selectedDifficulty)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .testTag("result_retake_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Replay, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Retake", fontFamily = AnimatedFontFamily, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("quiz_result_content"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Summary Score Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    border = BorderStroke(1.5.dp, badgeColor.copy(alpha = 0.4f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = badgeColor.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = badgeText,
                                color = badgeColor,
                                fontFamily = AnimatedFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Large Percentage Circle with Pop Border
                        Box(
                            modifier = Modifier
                                .size(118.dp)
                                .clip(CircleShape)
                                .background(badgeColor.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$percentage%",
                                    fontFamily = AnimatedFontFamily,
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = badgeColor
                                )
                                Text(
                                    text = "$correctCount / $total Correct",
                                    fontFamily = FriendlyBodyFontFamily,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Stat Chips
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            AnimatedResultStatPill(
                                label = "Correct",
                                value = "$correctCount",
                                color = DifficultyBeginner
                            )
                            AnimatedResultStatPill(
                                label = "Incorrect",
                                value = "$incorrectCount",
                                color = DifficultyAdvanced
                            )
                            AnimatedResultStatPill(
                                label = "Time Spent",
                                value = "${session.totalTimeSpentSeconds}s",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            // Question by Question Review Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Item Review & Legal Rationale",
                        fontFamily = AnimatedFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "$total Items",
                        fontFamily = AnimatedFontFamily,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // List of Answered Questions
            items(session.questions.size) { index ->
                val question = session.questions[index]
                val submission = session.submissions[index]
                val isCorrect = submission?.isCorrect == true
                val selectedIndex = submission?.selectedIndex

                OutlinedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(
                        1.5.dp,
                        if (isCorrect) DifficultyBeginner.copy(alpha = 0.5f) else DifficultyAdvanced.copy(alpha = 0.5f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(if (isCorrect) DifficultyBeginner else DifficultyAdvanced),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Item ${index + 1}",
                                    fontFamily = AnimatedFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            DifficultyBadge(difficulty = question.difficulty)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = question.questionText,
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Correct Answer Chip
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = DifficultyBeginner.copy(alpha = 0.12f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "✔ Correct Answer: ${question.options[question.correctIndex]}",
                                    fontFamily = FriendlyBodyFontFamily,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DifficultyBeginner
                                )
                                if (!isCorrect && selectedIndex != null && selectedIndex in question.options.indices) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "✖ Your Answer: ${question.options[selectedIndex]}",
                                        fontFamily = FriendlyBodyFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = DifficultyAdvanced
                                    )
                                } else if (!isCorrect && selectedIndex == null) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "⏱ Your Answer: Time Expired",
                                        fontFamily = FriendlyBodyFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = DifficultyAdvanced
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Reference: ${question.legalBasis} | Flipbook Page ${question.pageReference}",
                            fontFamily = AnimatedFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = question.explanation,
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedResultStatPill(
    label: String,
    value: String,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = color.copy(alpha = 0.12f)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontFamily = AnimatedFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = label,
                fontFamily = FriendlyBodyFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        }
    }
}
