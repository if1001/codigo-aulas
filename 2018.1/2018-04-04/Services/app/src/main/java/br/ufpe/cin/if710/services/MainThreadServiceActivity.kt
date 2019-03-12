package br.ufpe.cin.if710.services

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainThreadServiceActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_thread_service)

        val serviceIntent = Intent(this, MainThreadService::class.java)

        val startButton = findViewById<Button>(R.id.btn_StartServiceMainThread)
        val stopButton = findViewById<Button>(R.id.btn_StopServiceMainThread)
        val toastButton = findViewById<Button>(R.id.btn_Toast)
        startButton.setOnClickListener {
            startService(serviceIntent)
            Toast.makeText(applicationContext, "Iniciou o Service", Toast.LENGTH_LONG).show()
        }

        stopButton.setOnClickListener { stopService(serviceIntent) }
        toastButton.setOnClickListener { Toast.makeText(applicationContext, "Toast", Toast.LENGTH_SHORT).show() }
    }
}
