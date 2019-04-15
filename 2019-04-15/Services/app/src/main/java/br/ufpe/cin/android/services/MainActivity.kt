package br.ufpe.cin.android.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botao_mainThread.setOnClickListener { startActivity(Intent(applicationContext, MainThreadServiceActivity::class.java)) }

        botao_musicPlayer.setOnClickListener { startActivity(Intent(applicationContext, MusicPlayerActivity::class.java)) }

        botao_musicServiceWithoutBinding.setOnClickListener { startActivity(Intent(applicationContext, MusicPlayerNoBindingActivity::class.java)) }

        botao_musicServiceWithBinding.setOnClickListener { startActivity(Intent(applicationContext, MusicPlayerWithBindingActivity::class.java)) }

        botao_downloadService.setOnClickListener { startActivity(Intent(applicationContext, DownloadActivity::class.java)) }

        botao_broadcast.setOnClickListener { sendBroadcast(Intent("br.ufpe.cin.android.broadcasts.exemplo")) }

    }
}
