package br.ufpe.cin.if710.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicPlayerWithBindingService : Service() {
    private val TAG = "MusicPlayerWithBindingService"
    private var mPlayer: MediaPlayer? = null
    private val mStartID: Int = 0


    private val mBinder = MusicBinder()

    override fun onCreate() {
        super.onCreate()

        // configurar media player
        mPlayer = MediaPlayer.create(this, R.raw.moonlightsonata)

        if (null != mPlayer) {
            //fica em loop
            mPlayer!!.isLooping = true
        }


        // cria notificacao na area de notificacoes para usuario voltar p/ Activity
        val notificationIntent = Intent(applicationContext, MusicPlayerWithBindingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = Notification.Builder(
                applicationContext)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("Music Service rodando")
                .setContentText("Clique para acessar o player!")
                .setContentIntent(pendingIntent).build()

        // inicia em estado foreground, para ter prioridade na memoria
        // evita que seja facilmente eliminado pelo sistema
        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_STICKY
    }

    override fun onDestroy() {
        if (null != mPlayer) {
            mPlayer!!.stop()
            mPlayer!!.release()
        }
    }

    fun playMusic() {
        if (!mPlayer!!.isPlaying) {
            mPlayer!!.start()
        }
    }

    fun pauseMusic() {
        if (mPlayer!!.isPlaying) {
            mPlayer!!.pause()
        }
    }

    inner class MusicBinder : Binder() {
        internal// retorna a instancia do Service, para que clientes chamem metodos publicos
        val service: MusicPlayerWithBindingService
            get() = this@MusicPlayerWithBindingService

    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    companion object {
        private val NOTIFICATION_ID = 2
    }

}
