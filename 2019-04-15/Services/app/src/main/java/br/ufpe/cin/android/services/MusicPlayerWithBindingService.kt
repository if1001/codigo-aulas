package br.ufpe.cin.android.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MusicPlayerWithBindingService : Service() {
    private var mPlayer: MediaPlayer? = null

    private val mBinder = MusicBinder()

    override fun onCreate() {
        super.onCreate()

        //notification channel
        createChannel(this)

        // configurar media player
        mPlayer = MediaPlayer.create(this, R.raw.moonlightsonata)

        //fica em loop
        mPlayer?.isLooping = true

        // cria notificacao na area de notificacoes para usuario voltar p/ Activity
        val notificationIntent = Intent(applicationContext, MusicPlayerWithBindingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)


        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("Music Service rodando")
            .setContentText("Clique para acessar o player!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent).build()

        // inicia em estado foreground, para ter prioridade na memoria
        // evita que seja facilmente eliminado pelo sistema
        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_STICKY
    }

    override fun onDestroy() {
        mPlayer?.stop()
        mPlayer?.release()
    }

    private fun createChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.parseColor("#e8334a")
            notificationChannel.description = "notification channel description"
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    fun playMusic() {
        if (!mPlayer!!.isPlaying) {
            mPlayer?.start()
        }
    }

    fun pauseMusic() {
        if (mPlayer!!.isPlaying) {
            mPlayer?.pause()
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
        private val mStartID: Int = 0
        private val TAG = "MusicPlayerWithBindingService"
        private val CHANNEL_ID = "Regular"
        private val CHANNEL_NAME = "MusicService"

    }

}
