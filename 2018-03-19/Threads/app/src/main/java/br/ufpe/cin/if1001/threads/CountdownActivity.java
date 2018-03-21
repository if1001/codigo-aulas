package br.ufpe.cin.if1001.threads;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CountdownActivity extends Activity {

    TextView valorCountdown;
    int pontoPartida = 5;
    int pontoChegada = 0;
    int delay = 500;
    ContagemRegressivaTask tarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);


        valorCountdown = findViewById(R.id.valorContagem);
        Button iniciar = findViewById(R.id.btn_iniciarCountdown);
        Button cancelar = findViewById(R.id.btn_cancelarCountdown);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarefa = new ContagemRegressivaTask();
                tarefa.execute(pontoPartida,pontoChegada);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarefa.cancel(true);
            }
        });
    }

    void contagemRegressiva(int passos) {
        for (int i = passos; i>=0; i--) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            valorCountdown.setText(Integer.toString(i));
        }
    }
    class ContagemRegressivaTask extends AsyncTask<Integer,String,Void> {

        @Override
        protected void onPreExecute() {
            valorCountdown.setText("vai começar!");
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            //seria interessante checar se tem dois argumentos
            int pontoPartida = integers[0];
            int pontoChegada = integers[1];

            //assumindo que pontoChegada < pontoPartida
            while (pontoChegada<=pontoPartida) {
                if (!isCancelled()) {
                    //atualizar o TextView com o valor atual
                    //não pode dar setText... (thread separada)
                    //valorCountdown.setText("erro!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(String.valueOf(pontoPartida));
                    pontoPartida--;
                }
                else {
                    return null;
                }
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            //garantido de rodar na thread principal
            valorCountdown.setText(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(),"Terminou!",Toast.LENGTH_SHORT).show();
        }
    }

}