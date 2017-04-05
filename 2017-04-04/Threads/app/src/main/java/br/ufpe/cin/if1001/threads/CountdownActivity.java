package br.ufpe.cin.if1001.threads;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountdownActivity extends Activity {

    TextView valorCountdown;
    int pontoPartida = 5;
    int pontoChegada = 0;
    int delay = 500;
    ContaPassosTask t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);


        valorCountdown = (TextView) findViewById(R.id.valorContagem);
        Button iniciar = (Button) findViewById(R.id.btn_iniciarCountdown);
        Button cancelar = (Button) findViewById(R.id.btn_cancelarCountdown);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //contagemRegressiva(passos);
                t = new ContaPassosTask();
                t.execute(pontoPartida,pontoChegada);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t!=null) {
                    //valor true passado como argumento interrompe task
                    t.cancel(true);
                }
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

    private class ContaPassosTask extends AsyncTask<Integer,String,Void> {

        @Override
        protected void onPreExecute() {
            valorCountdown.setText("It's tiiiiiime!");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            //do jeito que esta programado, se nao checa a task com isCancelled,
            // pode continuar rodando mesmo apos task.cancel(false);

            int inicio = params[0];
            int fim = params[1];
            for (int i = inicio; i>=fim; i--) {
                //valorCountdown.setText("erro!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(Integer.toString(i));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... valor) {
            valorCountdown.setText(valor[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            valorCountdown.setText("Acabou! Tetra!");
        }
    }




}

/* */