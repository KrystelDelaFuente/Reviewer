package com.example.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DifficultyLevel
import com.example.data.model.QuizCategory
import com.example.data.model.TimerMode
import com.example.data.repository.QuizQuestionBank
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.DifficultyBadge
import com.example.ui.theme.AnimatedFontFamily
import com.example.ui.theme.FriendlyBodyFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizConfigScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val selectedDifficulty by viewModel.selectedDifficulty.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val questionCount by viewModel.questionCount.collectAsState()
    val timerConfig by viewModel.timerConfig.collectAsState()

    val availableQuestionCount = QuizQuestionBank.getQuestionsByCategory(
        category = selectedCategory,
        difficulty = selectedDifficulty
    ).size

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Quiz & Countdown Setup",
                        fontFamily = AnimatedFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(AppScreen.HOME) },
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Home"
                        )
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
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Selected Pool: $availableQuestionCount items",
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        val timerLabel = when (timerConfig.mode) {
                            TimerMode.PER_QUESTION -> "⚡ ${timerConfig.secondsPerQuestion}s / item"
                            TimerMode.TOTAL_EXAM -> "⏱ ${timerConfig.totalExamMinutes}m exam"
                            TimerMode.UNTIMED -> "Untimed"
                        }
                        Text(
                            text = timerLabel,
                            fontFamily = AnimatedFontFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            viewModel.startQuiz(
                                difficulty = selectedDifficulty,
                                category = selectedCategory
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("start_quiz_button"),
                        enabled = availableQuestionCount > 0,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Start Timed Practice ($questionCount Items)",
                            fontFamily = AnimatedFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("quiz_config_content"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Difficulty Level Selector
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "1. Difficulty Level",
                            fontFamily = AnimatedFontFamily,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Progressively test foundational statutes up to advanced case build-up.",
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // All / Mixed Option
                        PopDifficultyOptionRow(
                            title = "All Levels (Adaptive & Mixed)",
                            subtitle = "Comprehensive randomized mix across all difficulties",
                            isSelected = selectedDifficulty == null,
                            onClick = { viewModel.setDifficulty(null) }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Beginner
                        PopDifficultyOptionRow(
                            title = "Beginner Level",
                            subtitle = "PD 27, RA 6657 basics, 3 MFOs, logo, ARB qualifications",
                            isSelected = selectedDifficulty == DifficultyLevel.BEGINNER,
                            badge = { DifficultyBadge(difficulty = DifficultyLevel.BEGINNER) },
                            onClick = { viewModel.setDifficulty(DifficultyLevel.BEGINNER) }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Intermediate
                        PopDifficultyOptionRow(
                            title = "Intermediate Level",
                            subtitle = "LAD milestones, claim folder routing, tenancy rulings, LTC AO 4",
                            isSelected = selectedDifficulty == DifficultyLevel.INTERMEDIATE,
                            badge = { DifficultyBadge(difficulty = DifficultyLevel.INTERMEDIATE) },
                            onClick = { viewModel.setDifficulty(DifficultyLevel.INTERMEDIATE) }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Advanced
                        PopDifficultyOptionRow(
                            title = "Advanced Level",
                            subtitle = "ALI case build-up, NAMCI, Project SPLIT budget & ha, PRIME-HRM",
                            isSelected = selectedDifficulty == DifficultyLevel.ADVANCED,
                            badge = { DifficultyBadge(difficulty = DifficultyLevel.ADVANCED) },
                            onClick = { viewModel.setDifficulty(DifficultyLevel.ADVANCED) }
                        )
                    }
                }
            }

            // 2. Countdown Timer Settings
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "2. Countdown Timer",
                                fontFamily = AnimatedFontFamily,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        Text(
                            text = "Set the pacing and urgency for each question.",
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Timer Mode",
                            fontFamily = AnimatedFontFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FilterChip(
                                selected = timerConfig.mode == TimerMode.PER_QUESTION,
                                onClick = {
                                    viewModel.updateTimerConfig(
                                        timerConfig.copy(mode = TimerMode.PER_QUESTION)
                                    )
                                },
                                label = { Text("Per Question", fontFamily = AnimatedFontFamily) }
                            )
                            FilterChip(
                                selected = timerConfig.mode == TimerMode.TOTAL_EXAM,
                                onClick = {
                                    viewModel.updateTimerConfig(
                                        timerConfig.copy(mode = TimerMode.TOTAL_EXAM)
                                    )
                                },
                                label = { Text("Exam Total", fontFamily = AnimatedFontFamily) }
                            )
                            FilterChip(
                                selected = timerConfig.mode == TimerMode.UNTIMED,
                                onClick = {
                                    viewModel.updateTimerConfig(
                                        timerConfig.copy(mode = TimerMode.UNTIMED)
                                    )
                                },
                                label = { Text("Untimed", fontFamily = AnimatedFontFamily) }
                            )
                        }

                        if (timerConfig.mode == TimerMode.PER_QUESTION) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Seconds per Question",
                                fontFamily = AnimatedFontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf(15 to "15s (Blitz)", 30 to "30s", 45 to "45s", 60 to "60s").forEach { (sec, label) ->
                                    FilterChip(
                                        selected = timerConfig.secondsPerQuestion == sec,
                                        onClick = {
                                            viewModel.updateTimerConfig(
                                                timerConfig.copy(secondsPerQuestion = sec)
                                            )
                                        },
                                        label = { Text(label, fontFamily = AnimatedFontFamily, fontSize = 12.sp) }
                                    )
                                }
                            }
                        } else if (timerConfig.mode == TimerMode.TOTAL_EXAM) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Total Exam Time",
                                fontFamily = AnimatedFontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf(10 to "10 min", 15 to "15 min", 20 to "20 min", 30 to "30 min").forEach { (min, label) ->
                                    FilterChip(
                                        selected = timerConfig.totalExamMinutes == min,
                                        onClick = {
                                            viewModel.updateTimerConfig(
                                                timerConfig.copy(totalExamMinutes = min)
                                            )
                                        },
                                        label = { Text(label, fontFamily = AnimatedFontFamily, fontSize = 12.sp) }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Toggles
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Timer Ticking & Countdown Alert",
                                    fontFamily = AnimatedFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Urgent tone when less than 5 seconds remain",
                                    fontFamily = FriendlyBodyFontFamily,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = timerConfig.isSoundEnabled,
                                onCheckedChange = {
                                    viewModel.updateTimerConfig(timerConfig.copy(isSoundEnabled = it))
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Instant Legal Explanations",
                                    fontFamily = AnimatedFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Show statutory basis immediately after answering",
                                    fontFamily = FriendlyBodyFontFamily,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = timerConfig.instantExplanation,
                                onCheckedChange = {
                                    viewModel.updateTimerConfig(timerConfig.copy(instantExplanation = it))
                                }
                            )
                        }
                    }
                }
            }

            // 3. Question Count
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "3. Number of Questions",
                            fontFamily = AnimatedFontFamily,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Choose how many randomized items to take in this session.",
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(10, 15, 20, 30).forEach { count ->
                                FilterChip(
                                    selected = questionCount == count,
                                    onClick = { viewModel.setQuestionCount(count) },
                                    label = { Text("$count Items", fontFamily = AnimatedFontFamily) }
                                )
                            }
                        }
                    }
                }
            }

            // 4. Topic Category Filter
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "4. Focus Category",
                            fontFamily = AnimatedFontFamily,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Practice all subjects or narrow down to a specific chapter.",
                            fontFamily = FriendlyBodyFontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        QuizCategory.entries.forEach { cat ->
                            PopCategorySelectionRow(
                                category = cat,
                                isSelected = selectedCategory == cat,
                                onClick = { viewModel.setCategory(cat) }
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PopDifficultyOptionRow(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    badge: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
            else MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        fontFamily = AnimatedFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (badge != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        badge()
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = subtitle,
                    fontFamily = FriendlyBodyFontFamily,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                )
            }
            if (isSelected) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Text(
                        text = "Active",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = AnimatedFontFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PopCategorySelectionRow(
    category: QuizCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
            else MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(if (isSelected) 1.8.dp else 1.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = category.displayName,
                    fontFamily = AnimatedFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = category.description,
                    fontFamily = FriendlyBodyFontFamily,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                )
            }
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(5.dp))
                )
            }
        }
    }
}
