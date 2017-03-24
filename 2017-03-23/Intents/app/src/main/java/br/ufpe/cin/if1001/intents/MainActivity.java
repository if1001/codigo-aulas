package br.ufpe.cin.if1001.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText campoTexto = (EditText) findViewById(R.id.campoTexto);
        Button implicit = (Button) findViewById(R.id.botaoImplicit);
        Button explicit = (Button) findViewById(R.id.botaoExplicit);

        implicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = campoTexto.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(txt));
                startActivity(intent);

            }
        });

        explicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = campoTexto.getText().toString();
                Intent intent = new Intent(getApplicationContext(), OutraActivity.class);
                intent.putExtra("texto",txt);
                startActivity(intent);
            }
        });







    }
}
