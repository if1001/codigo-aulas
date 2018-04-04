package br.ufpe.cin.if710.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainThreadServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thread_service);

        final Intent serviceIntent = new Intent(this,MainThreadService.class);

        final Button startButton = findViewById(R.id.btn_StartServiceMainThread);
        final Button stopButton = findViewById(R.id.btn_StopServiceMainThread);
        final Button toastButton = findViewById(R.id.btn_Toast);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                startService(serviceIntent );
                Toast.makeText(getApplicationContext(),"Iniciou o Service",Toast.LENGTH_LONG).show();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                stopService(serviceIntent );
            }
        });
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Toast",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
