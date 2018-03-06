package br.ufpe.cin.if1001.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicPlayerNoBindingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_no_binding);

        // Intent usado para iniciar Player de Musica
        final Intent musicServiceIntent = new Intent(this, MusicPlayerNoBindingService.class);

        final Button startButton = (Button) findViewById(R.id.btn_StartService);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                // inicia o service usando o intent
                startService(musicServiceIntent);
            }
        });

        final Button stopButton = (Button) findViewById(R.id.btn_StopService);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                // para o service usando o mesmo intent
                stopService(musicServiceIntent);
            }
        });
    }
}
