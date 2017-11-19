package br.ufpe.cin.if1001.broadcastreceiver;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;

public class DynRecActivity extends Activity {

    private final IntentFilter intentFilter = new IntentFilter(MainActivity.INTENT_ACTION);
    private final DynamicReceiver receiver = new DynamicReceiver();
    private static final String[] SMS_PERMISSIONS = {
            Manifest.permission.RECEIVE_SMS
    };
    private static final int SMS_REQUEST = 710;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyn_rec);

        if (podeSMS()) {
            Button enviarBroadcast = (Button) findViewById(R.id.enviarBroadcastDynamic);
            enviarBroadcast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendBroadcast(new Intent(MainActivity.INTENT_ACTION),android.Manifest.permission.VIBRATE);
                }
            });
        }
        else {
            requestPermissions(SMS_PERMISSIONS, SMS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case SMS_REQUEST:
                if (!podeSMS()) {
                    Toast.makeText(this,"Sem permissão para SMS", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    private boolean podeSMS() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver,intentFilter);
        registerReceiver(smsReceiver,new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
        Toast.makeText(this, "Registrando...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        unregisterReceiver(smsReceiver);
        Toast.makeText(this, "Removendo registro...", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Chegou um SMS", Toast.LENGTH_SHORT).show();
            //int activePhone = TelephonyManager.getDefault().getCurrentPhoneType();
            //String format = (PHONE_TYPE_CDMA == activePhone) ? SmsConstants.FORMAT_3GPP2 : SmsConstants.FORMAT_3GPP;
            Object[] rawMsgs=(Object[])intent.getExtras().get("pdus");
            for (Object raw : rawMsgs) {
                SmsMessage msg = SmsMessage.createFromPdu((byte[])raw);
                if (msg.getMessageBody().toUpperCase().contains("IF710")) {
                    Toast.makeText(context, "Tem algo que nos interessa...", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };
}
