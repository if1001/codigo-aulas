package br.ufpe.cin.if1001.powermanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = (Button) findViewById(R.id.btn_keepScreen1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SemThreads.class));
            }
        });

        final Button button2 = (Button) findViewById(R.id.btn_keepScreen2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SemThreads.class));
            }
        });

        final Button button3 = (Button) findViewById(R.id.btn_wakelock);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SemThreads.class));
            }
        });

        final Button button4 = (Button) findViewById(R.id.btn_wakelock2);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SemThreads.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
