package br.ufpe.cin.if1001.activitylifecycle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView label;
    String status;

    private void geraToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        label = (TextView) findViewById(R.id.txtview);
        status = "onCreate() de " + this.getLocalClassName();
        label.setText(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("chave","valor");
    }

    @Override
    protected void onStart() {
        super.onStart();
        status = "onStart() de " + this.getLocalClassName();
        label.setText(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status = "onResume() de " + this.getLocalClassName();
        label.setText(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        status = "onRestart() de " + this.getLocalClassName();
        label.setText(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onPause() {
        super.onPause();
        status = "onPause() de " + this.getLocalClassName();
        label.setText(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onStop() {
        super.onStop();
        status = "onStop() de " + this.getLocalClassName();
        label.setText(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        status = "onDestroy() de " + this.getLocalClassName();
        label.setText(status);
        geraToast(status);
        Log.d(this.getLocalClassName(),status);
    }

}
