package br.ufpe.cin.if1001.powermanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class KeepScreenOn2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_screen_on2);

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setKeepScreenOn(true);
    }
}
