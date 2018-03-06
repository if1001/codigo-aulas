package br.ufpe.cin.if1001.aulainicial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcular = findViewById(R.id.calcular);
        final TextView resultado = findViewById(R.id.resultado);
        final EditText valor1 = findViewById(R.id.valor1);
        final EditText valor2 = findViewById(R.id.valor2);
        final EditText valor3 = findViewById(R.id.valor3);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valorFinal = 0;
                //TODO verificar se o texto digitado é
                int valor_1 = Integer.parseInt(valor1.getText().toString());
                int valor_2 = Integer.parseInt(valor2.getText().toString());
                int valor_3 = Integer.parseInt(valor3.getText().toString());
                valorFinal = (valor_1 + valor_2 +valor_3)/3.0;
                resultado.setText("Média: "+valorFinal);
            }
        });

    }
}
