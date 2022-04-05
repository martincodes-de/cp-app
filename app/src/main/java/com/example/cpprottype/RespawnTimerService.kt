package com.example.cpprottype

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.Button

class RespawnTimerService(buttonView: Button?, context: MainActivity) {
    private val buttonView: Button? = null
    private val context: MainActivity? = null
    private val mediaPlayer = MediaPlayer.create(context, R.raw.respawn)

    private val countDownTimer = object : CountDownTimer(AppSettings.respawnCountDown.toLong(), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            buttonView?.setText("RESPAWN (" + millisUntilFinished / 1000 + "s)")
        }

        @SuppressLint("ResourceAsColor")
        override fun onFinish() {
            buttonView?.setText("RESPAWN")
            mediaPlayer.start()
        }
    }

    fun startTimer() {
        countDownTimer.start()
    }

    fun stopTimer() {
        countDownTimer.cancel()
    }
}