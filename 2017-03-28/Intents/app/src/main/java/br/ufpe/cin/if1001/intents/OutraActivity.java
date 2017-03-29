package br.ufpe.cin.if1001.intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class OutraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);
        Intent intent = getIntent();
        String txt = intent.getStringExtra("texto");
        TextView texto = (TextView) findViewById(R.id.texto);
        texto.setText(txt);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.d();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
