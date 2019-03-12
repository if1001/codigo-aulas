package br.ufpe.cin.if710.services

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btn_MainThread = findViewById<View>(R.id.botao_mainThread) as Button
        btn_MainThread.setOnClickListener { startActivity(Intent(applicationContext, MainThreadServiceActivity::class.java)) }

        val btn_MusicPlayer = findViewById<View>(R.id.botao_musicPlayer) as Button
        btn_MusicPlayer.setOnClickListener { startActivity(Intent(applicationContext, MusicPlayerActivity::class.java)) }

        val btn_NoBindingService = findViewById<View>(R.id.botao_musicServiceWithoutBinding) as Button
        btn_NoBindingService.setOnClickListener { startActivity(Intent(applicationContext, MusicPlayerNoBindingActivity::class.java)) }

        val btn_BindingService = findViewById<View>(R.id.botao_musicServiceWithBinding) as Button
        btn_BindingService.setOnClickListener { startActivity(Intent(applicationContext, MusicPlayerWithBindingActivity::class.java)) }

        val btn_intentService = findViewById<View>(R.id.botao_downloadService) as Button
        btn_intentService.setOnClickListener { startActivity(Intent(applicationContext, DownloadActivity::class.java)) }


        val btn_sendBroadcast = findViewById<View>(R.id.botao_broadcast) as Button
        btn_sendBroadcast.setOnClickListener { sendBroadcast(Intent("br.ufpe.cin.if710.broadcasts.exemplo")) }
    }
}
