package com.example.cpprottype

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    private var backgroundView: View? = null
    private var respawnButton: Button? = null
    private var mainTimerService: MainTimerService? = null
    private var respawnTimerService: RespawnTimerService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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

        mainTimerService?.setTeam(buttonView.text.toString())

        if (respawnTimerService?.isRunning() == true) {
            respawnTimerService?.stopTimer()
            respawnTimerService?.resetTimer()
        }

        mainTimerService?.startTimer()
    }

    fun onRespawnBtnClick(buttonRawView: View) {
        if (!AppSettings.isRespawnTimerStartable) {
            return Snackbar.make(buttonRawView, "Respawntimer nicht gestartet wg. Einnahmeprozess",
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
        }

        respawnTimerService?.startTimer()
    }
}