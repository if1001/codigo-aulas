package br.ufpe.cin.if710.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MainThreadService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
