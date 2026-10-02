package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DifficultyLevel
import com.example.ui.theme.AnimatedFontFamily
import com.example.ui.theme.DifficultyAdvanced
import com.example.ui.theme.DifficultyAdvancedContainer
import com.example.ui.theme.DifficultyBeginner
import com.example.ui.theme.DifficultyBeginnerContainer
import com.example.ui.theme.DifficultyIntermediate
import com.example.ui.theme.DifficultyIntermediateContainer
import com.example.ui.theme.TimerCritical
import com.example.ui.theme.TimerSafe
import com.example.ui.theme.TimerWarning

@Composable
fun DifficultyBadge(
    difficulty: DifficultyLevel,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor, label) = when (difficulty) {
        DifficultyLevel.BEGINNER -> Triple(DifficultyBeginnerContainer, DifficultyBeginner, "Beginner ★")
        DifficultyLevel.INTERMEDIATE -> Triple(DifficultyIntermediateContainer, DifficultyIntermediate, "Intermediate ★★")
        DifficultyLevel.ADVANCED -> Triple(DifficultyAdvancedContainer, DifficultyAdvanced, "Advanced ★★★")
    }

    Surface(
        modifier = modifier
            .testTag("difficulty_badge_${difficulty.name.lowercase()}"),
        shape = RoundedCornerShape(14.dp),
        color = bgColor,
        border = androidx.compose.foundation.BorderStroke(1.dp, textColor.copy(alpha = 0.35f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(textColor)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                color = textColor,
                fontFamily = AnimatedFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TimerCountdownRing(
    remainingSeconds: Int,
    totalSeconds: Int,
    modifier: Modifier = Modifier
) {
    val progress = if (totalSeconds > 0) {
        (remainingSeconds.toFloat() / totalSeconds.toFloat()).coerceIn(0f, 1f)
    } else 0f

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "timer_progress"
    )

    val timerColor by animateColorAsState(
        targetValue = when {
            remainingSeconds <= 5 -> TimerCritical
            remainingSeconds <= 10 -> TimerWarning
            else -> TimerSafe
        },
        label = "timer_color"
    )

    // Animated bounce/pulse when time is running out (< 5 seconds)
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_clock")
    val pulseScale by if (remainingSeconds in 1..5) {
        infiniteTransition.animateFloat(
            initialValue = 0.95f,
            targetValue = 1.15f,
            animationSpec = infiniteRepeatable(
                animation = tween(350, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulse_scale"
        )
    } else {
        animateFloatAsState(targetValue = 1.0f, label = "idle_scale")
    }

    Box(
        modifier = modifier
            .scale(pulseScale)
            .testTag("timer_countdown_ring"),
        contentAlignment = Alignment.Center
    ) {
        // Soft glowing background circle
        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .background(timerColor.copy(alpha = 0.12f))
        )

        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.size(58.dp),
            color = timerColor,
            strokeWidth = 5.dp,
            trackColor = timerColor.copy(alpha = 0.22f),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$remainingSeconds",
                fontFamily = AnimatedFontFamily,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = timerColor
            )
            Text(
                text = "s",
                fontFamily = AnimatedFontFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = timerColor
            )
        }
    }
}

@Composable
fun TimerProgressBar(
    remainingSeconds: Int,
    totalSeconds: Int,
    modifier: Modifier = Modifier
) {
    val progress = if (totalSeconds > 0) {
        (remainingSeconds.toFloat() / totalSeconds.toFloat()).coerceIn(0f, 1f)
    } else 0f

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "linear_timer_progress"
    )

    val timerColor by animateColorAsState(
        targetValue = when {
            remainingSeconds <= 5 -> TimerCritical
            remainingSeconds <= 10 -> TimerWarning
            else -> TimerSafe
        },
        label = "timer_color_linear"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .testTag("timer_progress_bar")
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedProgress)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            timerColor,
                            timerColor.copy(alpha = 0.85f)
                        )
                    )
                )
        )
    }
}
