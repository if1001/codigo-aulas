package br.ufpe.cin.if710.services

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MusicPlayerActivity : Activity() {

    private var mPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        mPlayer = MediaPlayer.create(this, R.raw.moonlightsonata)


        val startButton = findViewById<View>(R.id.botaoPlay) as Button
        val pauseButton = findViewById<View>(R.id.botaoPause) as Button

        startButton.setOnClickListener {
            if (mPlayer != null) {
                mPlayer!!.start()
            }
        }

        pauseButton.setOnClickListener { mPlayer!!.pause() }
    }

    override fun onStop() {
        super.onStop()
        if (mPlayer != null) {
            mPlayer!!.release()
        }
    }
}
