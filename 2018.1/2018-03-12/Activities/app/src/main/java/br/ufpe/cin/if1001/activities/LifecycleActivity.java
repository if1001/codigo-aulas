package br.ufpe.cin.if1001.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LifecycleActivity extends Activity {
    String KEY_DIGITADO = "oQueFoiDigitado";
    String KEY_LIFECYCLE = "lifecycle";
    String status;

    //Campo de Texto usado para digitar algo
    EditText campoTexto;

    //TextViews para exibir o que foi digitado e o ciclo de vida da activity
    TextView textoDigitado, lifecycleLog;

    //Botao para efetuar ação
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        botao = findViewById(R.id.botao);
        campoTexto = findViewById(R.id.campoTexto);
        textoDigitado = findViewById(R.id.textoDigitado);
        lifecycleLog = findViewById(R.id.lifecycle);


        if (savedInstanceState != null) {
            String v = savedInstanceState.getString(KEY_DIGITADO);
            if (v != null) {
                textoDigitado.setText(v);
            }
            v = savedInstanceState.getString(KEY_LIFECYCLE);
            if (v != null) {
                lifecycleLog.setText(v);
            }
        }


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oQueFoiDigitado = campoTexto.getText().toString();
                if (oQueFoiDigitado.isEmpty()) {
                    geraToast("Digite algo, por favor.");
                }
                else {
                    textoDigitado.setText(oQueFoiDigitado);
                }

            }
        });

        status = "onCreate() de " + this.getLocalClassName();
        atualizaLifecycle(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);

    }

    @Override
    protected void onStart() {
        super.onStart();
        status = "onStart() de " + this.getLocalClassName();
        atualizaLifecycle(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status = "onResume() de " + this.getLocalClassName();
        atualizaLifecycle(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        status = "onRestart() de " + this.getLocalClassName();
        atualizaLifecycle(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onPause() {
        status = "onPause() de " + this.getLocalClassName();
        atualizaLifecycle(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
        super.onPause();
    }

    @Override
    protected void onStop() {
        status = "onStop() de " + this.getLocalClassName();
        atualizaLifecycle(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        status = "onDestroy() de " + this.getLocalClassName();
        atualizaLifecycle(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_DIGITADO, textoDigitado.getText().toString());
        outState.putString(KEY_LIFECYCLE, lifecycleLog.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void atualizaLifecycle(String msg) {
        String m = lifecycleLog.getText().toString();
        lifecycleLog.setText(msg + "\n" + m);
    }
    private void geraToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
