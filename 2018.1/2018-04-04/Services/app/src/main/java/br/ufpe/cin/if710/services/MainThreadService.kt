package br.ufpe.cin.if710.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MainThreadService : Service() {


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        try {
            Thread.sleep(2500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }
}
