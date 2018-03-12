package br.ufpe.cin.if1001.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LifecycleActivity extends Activity {
    //Campo de Texto usado para digitar algo
    EditText campoTexto;

    //TextViews para exibir o que foi digitado e o ciclo de vida da activity
    TextView textoDigitado,lifecycle;

    //Botao para efetuar ação
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        botao = findViewById(R.id.botao);
        campoTexto = findViewById(R.id.campoTexto);
        textoDigitado = findViewById(R.id.textoDigitado);
        lifecycle = findViewById(R.id.lifecycle);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oQueFoiDigitado = campoTexto.getText().toString();
                textoDigitado.setText(oQueFoiDigitado);
            }
        });
    }
}
