package br.ufpe.cin.if710.services

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class MusicPlayerNoBindingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player_no_binding)

        val serviceIntent = Intent(this, MusicPlayerNoBindingService::class.java)

        val startButton = findViewById<View>(R.id.btn_StartService) as Button
        val stopButton = findViewById<View>(R.id.btn_StopService) as Button
        startButton.setOnClickListener { startService(serviceIntent) }

        stopButton.setOnClickListener { stopService(serviceIntent) }
    }
}
