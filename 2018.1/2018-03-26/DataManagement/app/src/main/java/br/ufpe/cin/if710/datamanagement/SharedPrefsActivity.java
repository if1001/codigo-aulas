package br.ufpe.cin.if710.datamanagement;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SharedPrefsActivity extends Activity {
    private static String RECORDE = "recorde";
    private int maiorPontuacao = 0;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);
        //High Score
        final TextView recorde = findViewById(R.id.high_score_text);
        //Pontuação do Jogo
        final TextView pontuacaoAtual = findViewById(R.id.game_score_text);
        //Botão de Jogar
        final Button jogar = findViewById(R.id.play_button);
        //Botão de Resetar
        final Button resetar = findViewById(R.id.reset_button);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numeroAleatorio = new Random().nextInt(10000);
                pontuacaoAtual.setText(String.valueOf(numeroAleatorio));
                if (numeroAleatorio>maiorPontuacao) {
                    recorde.setText(String.valueOf(numeroAleatorio));
                    maiorPontuacao = numeroAleatorio;
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    prefsEditor.putInt(RECORDE,maiorPontuacao);
                    prefsEditor.commit();
                }
            }
        });

        resetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maiorPontuacao = 0;
                pontuacaoAtual.setText("");
                recorde.setText("0");
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                prefsEditor.remove(RECORDE);
                prefsEditor.commit();

            }
        });

        maiorPontuacao = sharedPreferences.getInt(RECORDE,0);
        recorde.setText(String.valueOf(maiorPontuacao));






















        /*
        //pega preferences
        sharedPreferences = getSharedPreferences("padrao",MODE_PRIVATE);

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gera novo número aleatório
                int novoNumero = new Random().nextInt(100000);
                //Atualiza número atual
                pontuacaoAtual.setText(String.valueOf(novoNumero));
                //Se for novo recorde
                if (novoNumero>maiorPontuacao) {
                    //Atualiza na tela
                    recorde.setText(String.valueOf(novoNumero));
                    maiorPontuacao = novoNumero;
                    //Atualiza nas preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(RECORDE, novoNumero);
                    editor.commit();
                }
            }
        });

        resetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Resetando na tela
                pontuacaoAtual.setText("0");
                recorde.setText("0");
                maiorPontuacao = 0;
                //Resetando na preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(RECORDE);
                editor.commit();
            }
        });

        //Pegando o valor atual para exibir na tela
        maiorPontuacao = sharedPreferences.getInt(RECORDE, 0);
        recorde.setText(String.valueOf(maiorPontuacao));
        /**/

    }
}
