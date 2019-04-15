package br.ufpe.cin.android.services

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_music_player.*

class MusicPlayerActivity : Activity() {

    private var mPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        //Nine Inch Nails Ghosts I-IV is licensed under a Creative Commons Attribution Non-Commercial Share Alike license.
        mPlayer = MediaPlayer.create(this, R.raw.ghosts)

        botaoPlay.setOnClickListener {
            mPlayer?.start()
        }

        botaoPause.setOnClickListener { mPlayer?.pause() }
    }

    override fun onStop() {
        super.onStop()
        mPlayer?.release()
    }
}
