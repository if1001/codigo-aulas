package br.ufpe.cin.if1001.audiovideocamera;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AudioPlaybackActivity extends Activity implements MediaPlayer.OnCompletionListener {
    private Button btnPlay,btnPause,btnStop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_playback);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop = (Button) findViewById(R.id.btnStop);


        try {
            //pode passar Uri para algum arquivo também...
            mp= MediaPlayer.create(this, R.raw.moonlightsonata);
            //mp  = MediaPlayer.create(this, Uri.parse("file:"));
            //create ja chama prepare(), deixa o player pronto para uso

            //o que acontece ao terminar de tocar musica
            mp.setOnCompletionListener(this);
        }
        catch (Exception e) {
            Toast.makeText(this,"Algum problema aconteceu no create()",Toast.LENGTH_SHORT).show();
        }

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        configuraBotoes(true,false,false);

    }

    private void configuraBotoes(boolean play, boolean pause, boolean stop) {
        btnPlay.setEnabled(play);
        btnPause.setEnabled(pause);
        btnStop.setEnabled(stop);
    }

    private void play() {
        //inicia playback - thread separada
        mp.start();

        //desabilita play
        configuraBotoes(false,true,true);
    }
    private void pause() {
        //pausa playback - ao clicar em play, retorna de onde parou
        mp.pause();

        //desabilita pause
        configuraBotoes(true,false,true);
    }
    private void stop() {
        //ao chamar stop() nao podemos chamar start novamente
        //eh necessario fazer o preparo do player novamente e 'voltar' ao inicio
        mp.stop();

        //desabilita tudo - explicacao abaixo
        configuraBotoes(false,false,false);

        //playback é assincrono, entao usamos postDelayed para nao conflitar com stop() acima
        //so podemos rodar o codigo abaixo apos concluir o stop()
        // nao tem um listener para algo do tipo onStopCompleted()...
        findViewById(android.R.id.content).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mp.prepare();
                    mp.seekTo(0);
                    configuraBotoes(true,false,false);
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Algum problema aconteceu no postDelayed de stop()",Toast.LENGTH_SHORT).show();
                }
            }
        }, 200);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stop();
    }










    @Override
    public void onBackPressed() {
        mp.stop();
        super.onBackPressed();
    }
    /**/
}
