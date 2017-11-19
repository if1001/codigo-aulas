package br.ufpe.cin.if1001.broadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public static final String INTENT_ACTION = "br.ufpe.cin.if1001.broadcasts.exemplo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enviarBroadcast = (Button) findViewById(R.id.enviarBroadcast);
        enviarBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(INTENT_ACTION),android.Manifest.permission.VIBRATE);
            }
        });

        Button abrirActivity = (Button) findViewById(R.id.abrirActivity);
        abrirActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DynRecActivity.class));
            }
        });

        Button bateriaActivity = (Button) findViewById(R.id.bateriaActivity);
        bateriaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BateriaActivity.class));
            }
        });



    }
}
