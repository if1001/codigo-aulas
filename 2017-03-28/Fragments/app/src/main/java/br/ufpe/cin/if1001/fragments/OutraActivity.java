package br.ufpe.cin.if1001.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OutraActivity extends Activity {

    public static final String EXTRA_MSG = "CHAVE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);
        TextView tv=(TextView)findViewById(R.id.txtView);
        tv.setText(getIntent().getStringExtra(EXTRA_MSG));
    }
}
