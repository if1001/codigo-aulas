package br.ufpe.cin.if1001.preferencefragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView textoUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open a User Preferences Entry Activity
        final Button button = (Button) findViewById(R.id.check_pref_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        MainActivity.this,
                        PrefsActivity.class));
            }
        });
        textoUsername = (TextView) findViewById(R.id.textoUsername);

    }

    @Override
    protected void onResume() {
        super.onResume();
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString(PrefsActivity.USERNAME,"nada escolhido...");
        textoUsername.setText(username);
    }
}
