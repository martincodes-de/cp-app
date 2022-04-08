package com.example.cpprottype

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.Button

class RespawnTimerService(buttonView: Button?, context: MainActivity) {
    private val buttonView: Button? = null
    private val context: MainActivity? = null
    private val mediaPlayer = MediaPlayer.create(context, R.raw.respawn)

    private var isRunning: Boolean = false

    private val countDownTimer = object : CountDownTimer(AppSettings.respawnCountDown.toLong(), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            buttonView?.setText("RESPAWN (" + millisUntilFinished / 1000 + "s)")
            isRunning = true
        }

        @SuppressLint("ResourceAsColor")
        override fun onFinish() {
            buttonView?.setText("RESPAWN")
            mediaPlayer.start()
            isRunning = false
        }
    }

    fun startTimer() {
        countDownTimer.start()
    }

    fun stopTimer() {
        countDownTimer.cancel()
        isRunning = false
    }

    fun resetTimer() {
        buttonView?.setText("RESPAWN")
        isRunning = false
    }

    fun isRunning(): Boolean {
        return isRunning
    }
}