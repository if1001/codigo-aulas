package br.ufpe.cin.if1001.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    private static String RECORDE = "recorde";
    private int maiorPontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //High Score
        final TextView recorde = (TextView) findViewById(R.id.high_score_text);
        //Pontuação do Jogo
        final TextView pontuacaoAtual = (TextView) findViewById(R.id.game_score_text);
        //Botão de Jogar
        final Button jogar = (Button) findViewById(R.id.play_button);
        //Botão de Resetar
        final Button resetar = (Button) findViewById(R.id.reset_button);

        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        int pontuacaoRecorde = prefs.getInt(RECORDE, 0);
        recorde.setText(String.valueOf(pontuacaoRecorde));


        //usar um shared preferences change listener para
        //atualizar automaticamente


        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int val = r.nextInt(1000);
                pontuacaoAtual.setText(String.valueOf(val));
                if (val > prefs.getInt(RECORDE,0)) {
                    recorde.setText(String.valueOf(val));
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt(RECORDE,val);
                    editor.commit();
                    maiorPontuacao = val;
                }
            }
        });
        resetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pontuacaoAtual.setText(String.valueOf(0));
                recorde.setText(String.valueOf(0));
                maiorPontuacao = 0;
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove(RECORDE);
                editor.commit();
            }
        });
    }


}





























/*


private static String HIGH_SCORE = "high_score";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final SharedPreferences prefs = getPreferences(MODE_PRIVATE);

		setContentView(R.layout.main);

		// High Score
		final TextView highScore = (TextView) findViewById(R.id.high_score_text);
		highScore.setText(String.valueOf(prefs.getInt(HIGH_SCORE, 0)));

		//Game Score
		final TextView gameScore = (TextView) findViewById(R.id.game_score_text);
		gameScore.setText(String.valueOf("0"));

		// Play Button
		final Button playButton = (Button) findViewById(R.id.play_button);
		playButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Random r = new Random();
				int val = r.nextInt(1000);
				gameScore.setText(String.valueOf(val));

				// Get Stored High Score
				if (val > prefs.getInt(HIGH_SCORE, 0)) {

					// Get and edit high score
					SharedPreferences.Editor editor = prefs.edit();
					editor.putInt(HIGH_SCORE, val);
					editor.commit();

					highScore.setText(String.valueOf(val));

				}
			}
		});

		// Reset Button
		final Button resetButton = (Button) findViewById(R.id.reset_button);
		resetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Set high score to 0
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt(HIGH_SCORE, 0);
				editor.commit();

				highScore.setText(String.valueOf("0"));
				gameScore.setText(String.valueOf("0"));

				}
		});

	}

 */
