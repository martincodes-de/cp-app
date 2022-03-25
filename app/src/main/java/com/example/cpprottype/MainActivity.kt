package com.example.cpprottype

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    private var backgroundView: View? = null
    private var respawnButton: Button? = null
    private var mainTimerService: MainTimerService? = null
    private var respawnTimerService: RespawnTimerService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true);

        backgroundView = findViewById(R.id.background)
        textView = findViewById(R.id.cpName)
        respawnButton = findViewById<Button>(R.id.respawnButton)

        textView?.setText(AppSettings.checkPointName)

        mainTimerService = MainTimerService(textView, backgroundView)
        respawnTimerService = RespawnTimerService(respawnButton, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Einstellungen")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.equals("Einstellungen")) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
    
    fun onBtnClick(buttonRawView: View) {
        val buttonView = buttonRawView as MaterialButton

        mainTimerService?.setTeam(buttonView.text.toString());
        mainTimerService?.startTimer()
    }

    fun onRespawnBtnClick(buttonRawView: View) {
        respawnTimerService?.startTimer()
    }
}