package br.ufpe.cin.android.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicPlayerNoBindingService : Service() {
    private val TAG = "MusicPlayerNoBindingService"

    private var mPlayer: MediaPlayer? = null
    private var mStartID: Int = 0

    override fun onCreate() {
        super.onCreate()

        // configurar media player
        mPlayer = MediaPlayer.create(this, R.raw.moonlightsonata)

        //nao deixa entrar em loop
        mPlayer?.isLooping = false

        // encerrar o service quando terminar a musica
        mPlayer?.setOnCompletionListener {
            // encerra se foi iniciado com o mesmo ID
            stopSelf(mStartID)
        }

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (null != mPlayer) {
            // ID para o comando de start especifico
            mStartID = startId

            /**/
            //se ja esta tocando...
            if (mPlayer!!.isPlaying) {
                //volta pra o inicio
                mPlayer?.seekTo(0)
            } else {
                // inicia musica
                mPlayer?.start()
            }
        }
        // nao reinicia service automaticamente se for eliminado
        return Service.START_NOT_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer?.stop()
        mPlayer?.release()
    }

    //nao eh possivel fazer binding com este service
    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
        //return null;
    }
}
