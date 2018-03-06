package br.ufpe.cin.if1001.recyclerview;

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

        Button b1 = (Button) findViewById(R.id.btn_RView1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RecyclerViewSimplesActivity.class));
            }
        });

        Button b2 = (Button) findViewById(R.id.btn_CView1);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CardViewActivity.class));
            }
        });

        Button b3 = (Button) findViewById(R.id.btn_CView2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CardViewClickActivity.class));
            }
        });

        Button b4 = (Button) findViewById(R.id.btn_CViewCursor);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CardViewCursorActivity.class));
            }
        });

        Button b5 = (Button) findViewById(R.id.btn_CViewGrid);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GridViewActivity.class));
            }
        });

        Button b6 = (Button) findViewById(R.id.btn_CViewChange);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RViewAulaActivity.class));
            }
        });
    }
}
