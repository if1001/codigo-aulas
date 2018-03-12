package br.ufpe.cin.if1001.activities;

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

        Button b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),LifecycleActivity.class));
            }
        });
        Button b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.cin.ufpe.br"));
            startActivity(i);
            }
        });
        Button b3 = findViewById(R.id.btn3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:0,0?q=Rua da Moeda"));
                startActivity(i);
            }
        });
        Button b4 = findViewById(R.id.btn4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StartActResultActivity.class));
            }
        });

        final EditText subject = findViewById(R.id.assunto);
        final EditText msg = findViewById(R.id.msg);
        Button implicit = findViewById(R.id.btnImplicit);
        Button explicit = findViewById(R.id.btnExplicit);

        implicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                i.putExtra(Intent.EXTRA_TEXT,msg.getText().toString());
                i.setType("text/plain");
                startActivity(i);
            }
        });

        explicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TxtActivity.class);
                i.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                i.putExtra(Intent.EXTRA_TEXT,msg.getText().toString());
                startActivity(i);
            }
        });

    }
}






/*





 */