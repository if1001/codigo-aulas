package br.ufpe.cin.if1001.packagemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public static final String QUERY_KEY = "PackageManagerQuery";
    public static final String GET_PKGS = "getInstalledPackages";
    public static final String GET_APPS = "getInstalledApplications";
    public static final String GET_BROADCASTS = "queryBroadcastReceivers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LauncherActivity.class));
            }
        });

        Button b2 = (Button) findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrefActivitiesActivity.class));

            }
        });

        Button b3 = (Button) findViewById(R.id.btn3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PackageManagerQueryActivity.class);
                i.putExtra(QUERY_KEY,GET_PKGS);
                startActivity(i);
            }
        });

        Button b4 = (Button) findViewById(R.id.btn4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PackageManagerQueryActivity.class);
                i.putExtra(QUERY_KEY,GET_APPS);
                startActivity(i);
            }
        });

        Button b5 = (Button) findViewById(R.id.btn5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PackageManagerQueryActivity.class);
                i.putExtra(QUERY_KEY,GET_BROADCASTS);
                startActivity(i);
            }
        });
    }
}
