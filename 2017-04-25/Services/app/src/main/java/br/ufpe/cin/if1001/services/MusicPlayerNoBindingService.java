package br.ufpe.cin.if1001.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicPlayerNoBindingService extends Service {

    private final String TAG = "MusicPlayerNoBindingService";

    private static final int NOTIFICATION_ID = 1;
    //Danilo vai testar com um video...
    private MediaPlayer mPlayer;
    private int mStartID;

    //nao esta usando pra nada...
    public MusicPlayerNoBindingService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // configurar media player
        //Nine Inch Nails Ghosts I-IV is licensed under a Creative Commons Attribution Non-Commercial Share Alike license.
        mPlayer = MediaPlayer.create(this, R.raw.ghosts);

        if (null != mPlayer) {
            //nao deixa entrar em loop
            mPlayer.setLooping(false);

            // encerrar o service quando terminar a musica
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // encerra se foi iniciado com o mesmo ID
                    stopSelf(mStartID);
                }
            });
        }

        // cria notificacao na area de notificacoes para usuario voltar p/ Activity
        final Intent notificationIntent = new Intent(getApplicationContext(), MusicPlayerNoBindingActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        final Notification notification = new Notification.Builder(
                getApplicationContext())
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("Musica tocando...")
                .setContentText("Clique para acessar o player!")
                .setContentIntent(pendingIntent).build();

        // inicia em estado foreground, para ter prioridade na memoria
        // evita que seja facilmente eliminado pelo sistema
        startForeground(NOTIFICATION_ID, notification);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        if (null != mPlayer) {
            // ID para o comando de start especifico
            mStartID = startid;

            /**/
            //se ja esta tocando...
            if (mPlayer.isPlaying()) {
                //volta pra o inicio
                mPlayer.seekTo(0);
            }
            else {
                // inicia musica
                mPlayer.start();
            }
        }
        // nao reinicia service automaticamente se for eliminado
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (null != mPlayer) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    //nao eh possivel fazer binding com este service
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
        //return null;
    }
}
