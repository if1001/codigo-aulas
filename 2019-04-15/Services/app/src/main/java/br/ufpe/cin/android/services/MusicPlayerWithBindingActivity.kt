package br.ufpe.cin.android.services

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_music_player_with_binding.*

class MusicPlayerWithBindingActivity : Activity() {
    internal var musicPlayerService: MusicPlayerWithBindingService? = null
    internal var isBound = false
    internal val TAG = "MusicBindingActivity"

    private val sConn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            Log.i(TAG, "Desconectou bound service")
            musicPlayerService = null
            isBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.i(TAG, "Binding com service")
            val serviceBinder = service as MusicPlayerWithBindingService.MusicBinder
            musicPlayerService = serviceBinder.service
            isBound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player_with_binding)
        // intent usado para iniciar o service
        val musicServiceIntent = Intent(this, MusicPlayerWithBindingService::class.java)
        startService(musicServiceIntent)


        botaoPlay.setOnClickListener {
            if (isBound) {
                musicPlayerService?.playMusic()
            } else {
                Toast.makeText(applicationContext, "Service ainda não fez binding", Toast.LENGTH_SHORT).show()
            }
        }
        botaoPause.setOnClickListener {
            if (isBound) {
                musicPlayerService?.pauseMusic()
            } else {
                Toast.makeText(applicationContext, "Service ainda não fez binding", Toast.LENGTH_SHORT).show()
            }
        }

        botaoStartService.setOnClickListener {
            if (!isBound) {
                startService(musicServiceIntent)
                Intent(applicationContext, MusicPlayerWithBindingService::class.java).also {
                    bindService(it, sConn, Context.BIND_AUTO_CREATE)
                }
            }
        }
        botaoStopService.setOnClickListener {
            if (isBound) {
                unbindService(sConn)
                stopService(musicServiceIntent)
                isBound = false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (!isBound) {
            Toast.makeText(applicationContext, "Binding...", Toast.LENGTH_SHORT).show()
            val i = Intent(this,
                MusicPlayerWithBindingService::class.java)
            bindService(i, sConn, Context.BIND_AUTO_CREATE)
            /*
            Intent(this,
                MusicPlayerWithBindingService::class.java)
                .also {
                bindService(it, sConn, Context.BIND_AUTO_CREATE)
            }
            */
        }
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "Unbinding...", Toast.LENGTH_SHORT).show()
        if (isBound) {
            unbindService(sConn)
            isBound = false
        }
    }
}
