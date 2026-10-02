package com.example.ui.util

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class SoundAndHapticsHelper(private val context: Context) {

    private var toneGen: ToneGenerator? = null

    init {
        try {
            toneGen = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 70)
        } catch (_: Exception) {
            toneGen = null
        }
    }

    fun playTickTone(isEnabled: Boolean) {
        if (!isEnabled) return
        try {
            toneGen?.startTone(ToneGenerator.TONE_PROP_BEEP, 70)
        } catch (_: Exception) {}
    }

    fun playUrgentTickTone(isEnabled: Boolean) {
        if (!isEnabled) return
        try {
            toneGen?.startTone(ToneGenerator.TONE_CDMA_PIP, 100)
        } catch (_: Exception) {}
    }

    fun playCorrectTone(isEnabled: Boolean) {
        if (!isEnabled) return
        try {
            toneGen?.startTone(ToneGenerator.TONE_PROP_ACK, 180)
        } catch (_: Exception) {}
    }

    fun playIncorrectTone(isEnabled: Boolean) {
        if (!isEnabled) return
        try {
            toneGen?.startTone(ToneGenerator.TONE_PROP_NACK, 250)
        } catch (_: Exception) {}
    }

    fun vibrateShort(isEnabled: Boolean) {
        if (!isEnabled) return
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vm?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(50)
            }
        } catch (_: Exception) {}
    }

    fun vibrateWarning(isEnabled: Boolean) {
        if (!isEnabled) return
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vm?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(150)
            }
        } catch (_: Exception) {}
    }

    fun release() {
        try {
            toneGen?.release()
            toneGen = null
        } catch (_: Exception) {}
    }
}
