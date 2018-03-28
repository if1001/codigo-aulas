package br.ufpe.cin.if710.datamanagement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrefsActivity extends Activity {
    public static final String USERNAME = "uname";
    TextView textoUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);
        final Button b1 = findViewById(R.id.edit_pref_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        PrefsActivity.this,
                        PrefsEditActivity.class));
            }
        });
        final Button b2 = findViewById(R.id.check_pref_button);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        PrefsActivity.this,
                        PrefsMenuActivity.class));
            }
        });
        textoUsername = findViewById(R.id.textoUsername);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Obtém o valor para a preference de nome de usuário
        String user_name = prefs.getString(USERNAME,"nada escolhido...");
        textoUsername.setText(user_name);
    }








    /*
    @Override
    protected void onResume() {
        super.onResume();
        //Carrega o objeto SharedPreferences
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Obtém o valor para a preference de nome de usuário
        String username = prefs.getString(PrefsMenuActivity.USERNAME,"nada escolhido...");
        //seta o TextView para conter este valor
        textoUsername.setText(username);
    }
    /**/


}