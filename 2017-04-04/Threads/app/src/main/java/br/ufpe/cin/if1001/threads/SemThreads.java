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

public class SemThreads extends Activity {
    private Bitmap mBitmap;
    private ImageView mIView;
    private int mDelay = 5000;
    int toasts = 0;
    private TextView contadorToasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threads);
        mIView = (ImageView) findViewById(R.id.imageView);
        contadorToasts = (TextView) findViewById(R.id.contadorToasts);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIcon();
                if (null != mBitmap) {
                    mIView.setImageBitmap(mBitmap);
                }
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
        try {
            Thread.sleep(mDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.painter);
    }


}
