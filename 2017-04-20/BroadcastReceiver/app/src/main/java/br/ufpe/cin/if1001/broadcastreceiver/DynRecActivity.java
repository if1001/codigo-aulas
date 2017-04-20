package br.ufpe.cin.if1001.broadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DynRecActivity extends Activity {

    private final IntentFilter intentFilter = new IntentFilter(MainActivity.INTENT_ACTION);
    private final DynamicReceiver receiver = new DynamicReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyn_rec);

        Button enviarBroadcast = (Button) findViewById(R.id.enviarBroadcastDynamic);
        enviarBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(MainActivity.INTENT_ACTION),android.Manifest.permission.VIBRATE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver,intentFilter);
        Toast.makeText(this, "Registrando...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
        Toast.makeText(this, "Removendo registro...", Toast.LENGTH_SHORT).show();
    }
}
