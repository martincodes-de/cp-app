package com.example.cpprottype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class SettingsActivity : AppCompatActivity() {
    private var nameField: EditText? = null
    private var mainTimerField: EditText? = null
    private var respawnTimerField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        nameField = findViewById(R.id.checkPointNameField)
        mainTimerField = findViewById(R.id.mainTimerField)
        respawnTimerField = findViewById(R.id.respawnTimerField)

        nameField?.setText(AppSettings.checkPointName)
        mainTimerField?.setText(AppSettings.countDown.toString())
        respawnTimerField?.setText(AppSettings.respawnCountDown.toString())
    }

    fun saveSettings(view: View) {
        AppSettings.checkPointName = nameField?.text.toString()
        AppSettings.countDown = mainTimerField?.text.toString().toInt()
        AppSettings.respawnCountDown = respawnTimerField?.text.toString().toInt()
        Snackbar.make(view, "Einstellungen aktualisiert", LENGTH_LONG).show()
    }
}