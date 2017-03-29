package br.ufpe.cin.if1001.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //define um layout que adiciona um fragmento estaticamente
        //por meio da tag <fragment>
        setContentView(R.layout.activity_fragment);

        //a UI inteira consiste do fragmento
    }

    public void iniciarActivity(View v) {
        Intent i = new Intent(this, OutraActivity.class);
        i.putExtra(OutraActivity.EXTRA_MSG,getString(R.string.algumaString));
        startActivity(i);
    }

}
