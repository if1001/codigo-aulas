package br.ufpe.cin.if1001.threads;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PostDelayedActivity extends Activity implements Runnable {

    private View mainView = null;
    private static final int INTERVALO = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_delayed);
        mainView = findViewById(android.R.id.content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        run();
    }

    @Override
    protected void onStop() {
        mainView.removeCallbacks(this);
        super.onStop();
    }


    @Override
    public void run() {
        Toast.makeText(PostDelayedActivity.this, "vou aparecer de novo...", Toast.LENGTH_SHORT).show();
        mainView.postDelayed(this, INTERVALO);
    }
}
