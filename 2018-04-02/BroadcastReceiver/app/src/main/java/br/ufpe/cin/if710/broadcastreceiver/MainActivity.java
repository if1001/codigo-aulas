package br.ufpe.cin.if710.broadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public static final String STA_BROADCAST_ACTION = "br.ufpe.cin.if710.broadcasts.exemplo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enviarBroadcast = findViewById(R.id.enviarBroadcast);
        enviarBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(STA_BROADCAST_ACTION));
            }
        });

        Button abrirActivity = findViewById(R.id.abrirActivity);
        abrirActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DynRecActivity.class));
            }
        });

        Button bateriaActivity = findViewById(R.id.bateriaActivity);
        bateriaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BateriaActivity.class));

            }
        });
    }
}
