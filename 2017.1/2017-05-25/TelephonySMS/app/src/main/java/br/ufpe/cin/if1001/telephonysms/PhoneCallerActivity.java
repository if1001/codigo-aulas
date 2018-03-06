package br.ufpe.cin.if1001.telephonysms;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneCallerActivity extends Activity implements View.OnClickListener {
    EditText num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        num = (EditText) findViewById(R.id.numberToDial);

        Button dialer = (Button) findViewById(R.id.btnDial);
        dialer.setOnClickListener(this);
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onClick(View v) {
        String numberToDial = num.getText().toString();
        Uri data = Uri.parse("tel:"+numberToDial);
        //precisa de permissao CALL_PHONE
        Intent i = new Intent(Intent.ACTION_CALL, data);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(i);
        }

    }
}
