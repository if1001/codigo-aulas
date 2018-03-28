package br.ufpe.cin.if710.datamanagement;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PrefsEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs_edit);
        final EditText campoTexto = findViewById(R.id.editTextUsername);
        Button b = findViewById(R.id.salvarUsername);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oQueFoiDigitado = campoTexto.getText().toString();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(PrefsActivity.USERNAME, oQueFoiDigitado);
                editor.commit();
                finish();
            }
        });


























        /*
        //Carrega o objeto preferences e o valor para username
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PrefsEditActivity.this);
        String username = prefs.getString(PrefsMenuActivity.USERNAME, "nada");
        //atualiza edittext com o valor
        campoTexto.setText(username);

        Button b = findViewById(R.id.salvarUsername);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pega o que foi digitado
                String oQueFoiDigitado = campoTexto.getText().toString();
                //obtém objeto que permite manipular SharedPreferences
                SharedPreferences.Editor prefsEditor = prefs.edit();
                //Atualiza o valor associado com USERNAME
                prefsEditor.putString(PrefsMenuActivity.USERNAME, oQueFoiDigitado);
                //Salva a alteração
                prefsEditor.commit();
                //Fecha a Activity
                finish();
            }
        });
        /**/
    }
}
