package com.example.cpprottype

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView

class MainTimerService(textView: TextView?, backgroundView: View?) {
    private val textView: TextView? = null
    private val backgroundView: View? = null
    private var team: String? = null

    private val countDownTimer = object : CountDownTimer(AppSettings.countDown.toLong(), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            textView?.setText(AppSettings.checkPointName + " - " + millisUntilFinished / 1000 + "s")
            AppSettings.isRespawnTimerStartable = false
        }

        @SuppressLint("ResourceAsColor")
        override fun onFinish() {
            backgroundView?.setBackgroundResource(getBackgroundColor(team))
            textView?.setText(AppSettings.checkPointName)
            AppSettings.isRespawnTimerStartable = true
        }
    }

    fun startTimer() {
        countDownTimer.start()
    }

    fun stopTimer() {
        countDownTimer.cancel()
        AppSettings.isRespawnTimerStartable = true
    }

    fun setTeam(newTeam: String) {
        team = newTeam
    }

    private fun getBackgroundColor(team: String?): Int {
        return when (team) {
            "ROT" -> R.color.background_red
            "BLAU" -> R.color.background_blue
            else -> {
                R.color.teal_200
            }
        }
    }
}