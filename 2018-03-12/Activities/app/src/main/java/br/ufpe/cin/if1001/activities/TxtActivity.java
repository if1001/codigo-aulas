package br.ufpe.cin.if1001.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TxtActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt);

        Intent i = getIntent();
        String assunto = i.getStringExtra(Intent.EXTRA_SUBJECT);
        String texto = i.getStringExtra(Intent.EXTRA_TEXT);

        TextView t1 = findViewById(R.id.assunto);
        t1.setText(assunto);
        TextView t2 = findViewById(R.id.texto);
        t2.setText(texto);
    }
}
