package br.ufpe.cin.if1001.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BateriaBaixaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification.Builder notBuilder = new Notification.Builder(context).setContentTitle("Bateria fraca!").setContentText("conecte o carregador!").setSmallIcon(android.R.drawable.ic_dialog_alert);
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1,notBuilder.build());

        String a = intent.getAction();
        Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
    }
}