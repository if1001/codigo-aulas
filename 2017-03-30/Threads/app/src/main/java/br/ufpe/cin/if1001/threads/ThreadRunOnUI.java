package br.ufpe.cin.if1001.threads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ThreadRunOnUI extends Activity {

    private Bitmap mBitmap;
    private ImageView mImageView;
    private TextView contadorToasts;
    private int mDelay = 5000;
    int toasts = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threads);

        mImageView = (ImageView) findViewById(R.id.imageView);
        contadorToasts = (TextView) findViewById(R.id.contadorToasts);

        final Button button = (Button) findViewById(R.id.loadButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIcon();
            }
        });

        final Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toasts++;
                contadorToasts.setText(getString(R.string.contador_de_toasts) + toasts);
                Toast.makeText(getApplicationContext(), "Estou trabalhando... (" + toasts + ")", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadIcon() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(mDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.painter);

                //mImageView.setImageBitmap(mBitmap);
                ThreadRunOnUI.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(mBitmap);
                    }
                });
            }
        }).start();
    }

}
