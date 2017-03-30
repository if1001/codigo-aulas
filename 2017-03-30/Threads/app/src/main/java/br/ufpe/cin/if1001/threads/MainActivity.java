package br.ufpe.cin.if1001.threads;

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

        final Button button1 = (Button) findViewById(R.id.btn_nothreads);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SemThreads.class);
                startActivity(intent);
            }
        });

        final Button button2 = (Button) findViewById(R.id.btn_thread_anr);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreadANR.class);
                startActivity(intent);
            }
        });

        final Button button3 = (Button) findViewById(R.id.btn_thread_simple);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreadSimples.class);
                startActivity(intent);
            }
        });

        final Button button4 = (Button) findViewById(R.id.btn_thread_viewpost);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreadViewPost.class);
                startActivity(intent);
            }
        });

        final Button button5 = (Button) findViewById(R.id.btn_thread_runonui);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreadRunOnUI.class);
                startActivity(intent);
            }
        });

        final Button button6 = (Button) findViewById(R.id.btn_thread_asynctask);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
            }
        });
    }


}
