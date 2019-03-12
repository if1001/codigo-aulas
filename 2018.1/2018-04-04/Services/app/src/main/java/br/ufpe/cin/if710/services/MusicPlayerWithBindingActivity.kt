package br.ufpe.cin.if710.services

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
            musicPlayerService = (service as MusicPlayerWithBindingService.MusicBinder).service
            isBound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player_with_binding)
        // intent usado para iniciar o service
        val musicServiceIntent = Intent(this, MusicPlayerWithBindingService::class.java)
        startService(musicServiceIntent)


        val startButton = findViewById<View>(R.id.botaoPlay) as Button
        startButton.setOnClickListener {
            if (isBound) {
                musicPlayerService!!.playMusic()
            } else {
                Toast.makeText(applicationContext, "Service ainda não fez binding", Toast.LENGTH_SHORT).show()
            }
        }
        val pauseButton = findViewById<View>(R.id.botaoPause) as Button
        pauseButton.setOnClickListener {
            if (isBound) {
                musicPlayerService!!.pauseMusic()
            } else {
                Toast.makeText(applicationContext, "Service ainda não fez binding", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (!isBound) {
            Toast.makeText(applicationContext, "Binding...", Toast.LENGTH_SHORT).show()
            val bindIntent = Intent(this, MusicPlayerWithBindingService::class.java)
            isBound = bindService(bindIntent, sConn, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "Unbinding...", Toast.LENGTH_SHORT).show()
        unbindService(sConn)
        isBound = false
    }


/* nao eh interessante, pois para a musica ao eliminar Activity da Memoria
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            Intent bindIntent = new Intent(this,MusicPlayerWithBindingService.class);
            stopService(bindIntent);
        }
    }
/**/

}
