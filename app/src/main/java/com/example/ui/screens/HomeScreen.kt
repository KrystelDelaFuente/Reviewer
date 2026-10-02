package com.example.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DifficultyLevel
import com.example.data.model.QuizCategory
import com.example.data.repository.QuizQuestionBank
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.DifficultyBadge
import com.example.ui.theme.AnimatedFontFamily
import com.example.ui.theme.DifficultyAdvanced
import com.example.ui.theme.DifficultyAdvancedContainer
import com.example.ui.theme.DifficultyBeginner
import com.example.ui.theme.DifficultyBeginnerContainer
import com.example.ui.theme.DifficultyIntermediate
import com.example.ui.theme.DifficultyIntermediateContainer
import com.example.ui.theme.FriendlyBodyFontFamily
import com.example.ui.theme.PopSkyBlue
import com.example.ui.theme.PopSkyBlueContainer
import com.example.ui.theme.PopVioletTertiary
import com.example.ui.theme.PopYellowSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val history by viewModel.quizHistory.collectAsState()
    val bookmarks by viewModel.bookmarkedQuestions.collectAsState()
    val mistakes by viewModel.mistakeQuestions.collectAsState()
    val timerConfig by viewModel.timerConfig.collectAsState()

    val totalQuizzes = history.size
    val averageScore = if (history.isNotEmpty()) {
        history.map { it.percentage }.average().toInt()
    } else 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "DAR Reviewer",
                                fontFamily = AnimatedFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Timed Quiz • Flipbook Edition",
                                fontFamily = FriendlyBodyFontFamily,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.toggleSound() },
                        modifier = Modifier.testTag("sound_toggle_button")
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (timerConfig.isSoundEnabled) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (timerConfig.isSoundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                                    contentDescription = if (timerConfig.isSoundEnabled) "Mute Sound" else "Enable Sound",
                                    tint = if (timerConfig.isSoundEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                    IconButton(
                        onClick = { viewModel.navigateTo(AppScreen.QUIZ_CONFIG) },
                        modifier = Modifier.testTag("settings_button")
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Quiz Configuration",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("home_screen_content"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Hero Animated Banner
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("hero_banner_card"),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                        Color.Transparent
                                    )
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = MaterialTheme.colorScheme.primary
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Timer,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "COUNTDOWN PRACTICE",
                                            fontFamily = AnimatedFontFamily,
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.5.sp
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                                ) {
                                    Text(
                                        text = "⚡ 60 Bank Items",
                                        fontFamily = AnimatedFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "Train Under The Clock!",
                                fontFamily = AnimatedFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                lineHeight = 30.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Sharpen your recall on PD 27, RA 6657, RA 11953, Project SPLIT, and tenancy court cases with real-time pressure.",
                                fontFamily = FriendlyBodyFontFamily,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f),
                                lineHeight = 19.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Button(
                                    onClick = {
                                        viewModel.startQuiz(
                                            difficulty = null,
                                            category = QuizCategory.ALL
                                        )
                                    },
                                    modifier = Modifier
                                        .weight(1.3f)
                                        .height(48.dp)
                                        .testTag("quick_start_button"),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    shape = RoundedCornerShape(14.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        "Rapid 15 Blitz",
                                        fontFamily = AnimatedFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                }

                                OutlinedButton(
                                    onClick = { viewModel.navigateTo(AppScreen.QUIZ_CONFIG) },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp)
                                        .testTag("customize_quiz_button"),
                                    shape = RoundedCornerShape(14.dp),
                                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Speed,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        "Custom",
                                        fontFamily = AnimatedFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Quick Stats Row with Bouncy Icons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AnimatedStatCard(
                        title = "Tests Taken",
                        value = "$totalQuizzes",
                        icon = Icons.Default.Quiz,
                        iconTint = MaterialTheme.colorScheme.primary,
                        iconBg = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.weight(1f)
                    )
                    AnimatedStatCard(
                        title = "Accuracy",
                        value = if (totalQuizzes > 0) "$averageScore%" else "—",
                        icon = Icons.Default.Speed,
                        iconTint = PopYellowSecondary,
                        iconBg = DifficultyIntermediateContainer,
                        modifier = Modifier.weight(1f)
                    )
                    AnimatedStatCard(
                        title = "Bookmarked",
                        value = "${bookmarks.size}",
                        icon = Icons.Default.Bookmark,
                        iconTint = PopVioletTertiary,
                        iconBg = MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.weight(1f),
                        onClick = { if (bookmarks.isNotEmpty()) viewModel.navigateTo(AppScreen.BOOKMARKS) }
                    )
                }
            }

            // Section Header: Difficulty Levels
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = PopYellowSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Choose Your Level",
                        fontFamily = AnimatedFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            // 1. Beginner Level Card
            item {
                AnimatedDifficultyCard(
                    difficulty = DifficultyLevel.BEGINNER,
                    accentColor = DifficultyBeginner,
                    containerColor = DifficultyBeginnerContainer,
                    itemCount = QuizQuestionBank.getQuestionsByDifficulty(DifficultyLevel.BEGINNER).size,
                    onStartClick = { viewModel.startQuiz(difficulty = DifficultyLevel.BEGINNER) },
                    modifier = Modifier.testTag("beginner_level_card")
                )
            }

            // 2. Intermediate Level Card
            item {
                AnimatedDifficultyCard(
                    difficulty = DifficultyLevel.INTERMEDIATE,
                    accentColor = DifficultyIntermediate,
                    containerColor = DifficultyIntermediateContainer,
                    itemCount = QuizQuestionBank.getQuestionsByDifficulty(DifficultyLevel.INTERMEDIATE).size,
                    onStartClick = { viewModel.startQuiz(difficulty = DifficultyLevel.INTERMEDIATE) },
                    modifier = Modifier.testTag("intermediate_level_card")
                )
            }

            // 3. Advanced Level Card
            item {
                AnimatedDifficultyCard(
                    difficulty = DifficultyLevel.ADVANCED,
                    accentColor = DifficultyAdvanced,
                    containerColor = DifficultyAdvancedContainer,
                    itemCount = QuizQuestionBank.getQuestionsByDifficulty(DifficultyLevel.ADVANCED).size,
                    onStartClick = { viewModel.startQuiz(difficulty = DifficultyLevel.ADVANCED) },
                    modifier = Modifier.testTag("advanced_level_card")
                )
            }

            // Study & Review Hub
            item {
                Text(
                    text = "Flipbook Reviewer Tools",
                    fontFamily = AnimatedFontFamily,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Digital Flipbook & Self-Test Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AnimatedStudyHubCard(
                        title = "Cheat Sheet Notes",
                        subtitle = "Numbers & Jurisprudence",
                        icon = Icons.Default.Description,
                        badgeText = "9 Chapters",
                        iconBg = PopSkyBlueContainer,
                        iconTint = PopSkyBlue,
                        onClick = { viewModel.navigateTo(AppScreen.FLIPBOOK_NOTES) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("study_notes_button")
                    )

                    AnimatedStudyHubCard(
                        title = "Self-Test Deck",
                        subtitle = "26 Flip Questions",
                        icon = Icons.Default.HelpOutline,
                        badgeText = "Page 32",
                        iconBg = DifficultyIntermediateContainer,
                        iconTint = DifficultyIntermediate,
                        onClick = { viewModel.navigateTo(AppScreen.SELF_TEST) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("self_test_button")
                    )
                }
            }

            // Mistakes Practice (if any mistakes exist)
            if (mistakes.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.navigateTo(AppScreen.MISTAKES_REVIEW) }
                            .testTag("mistakes_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = DifficultyAdvancedContainer
                        ),
                        border = BorderStroke(1.5.dp, DifficultyAdvanced.copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(DifficultyAdvanced),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ErrorOutline,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Practice Missed Questions",
                                    fontFamily = AnimatedFontFamily,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DifficultyAdvanced
                                )
                                Text(
                                    text = "${mistakes.size} questions need review",
                                    fontFamily = FriendlyBodyFontFamily,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = DifficultyAdvanced
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedDifficultyCard(
    difficulty: DifficultyLevel,
    accentColor: Color,
    containerColor: Color,
    itemCount: Int,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.5.dp, accentColor.copy(alpha = 0.35f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DifficultyBadge(difficulty = difficulty)

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = accentColor,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${difficulty.recommendedSeconds}s / item",
                            fontFamily = AnimatedFontFamily,
                            fontSize = 12.sp,
                            color = accentColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = difficulty.subtitle,
                fontFamily = AnimatedFontFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = difficulty.description,
                fontFamily = FriendlyBodyFontFamily,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = containerColor
                ) {
                    Text(
                        text = "● $itemCount Questions",
                        fontFamily = AnimatedFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                Button(
                    onClick = onStartClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentColor
                    ),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 9.dp)
                ) {
                    Text(
                        "Play ${difficulty.title}",
                        fontFamily = AnimatedFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedStatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    iconBg: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontFamily = AnimatedFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = title,
                fontFamily = FriendlyBodyFontFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun AnimatedStudyHubCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    badgeText: String,
    iconBg: Color,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = iconBg
                ) {
                    Text(
                        text = badgeText,
                        fontFamily = AnimatedFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = iconTint,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                fontFamily = AnimatedFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                fontFamily = FriendlyBodyFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
