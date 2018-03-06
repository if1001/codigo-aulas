package br.ufpe.cin.if1001.adapters;

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

        Button btn_ListActivity = (Button) findViewById(R.id.btn_ListActivity);

        btn_ListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListaActivity.class));
            }
        });

        Button btn_ListActivityLayout = (Button) findViewById(R.id.btn_ListActivityLayout);

        btn_ListActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListaLayoutActivity.class));
            }
        });

        Button btn_ListView = (Button) findViewById(R.id.btn_ListView);

        btn_ListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListViewActivity.class));
            }
        });

        Button btn_Spinner = (Button) findViewById(R.id.btn_Spinner);

        btn_Spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SpinnerActivity.class));
            }
        });

        Button btn_GridView = (Button) findViewById(R.id.btn_GridView);

        btn_GridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GridViewActivity.class));
            }
        });

        Button btn_AutoCompleteTextView = (Button) findViewById(R.id.btn_AutoCompleteTextView);

        btn_AutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AutoCompleteActivity.class));
            }
        });

        Button btn_CustomAdapter = (Button) findViewById(R.id.btn_CustomAdapter);

        btn_CustomAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CustomAdapterActivity.class));
            }
        });

        Button btn_ViewHolder = (Button) findViewById(R.id.btn_ViewHolder);

        btn_ViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewHolderActivity.class));
            }
        });

        Button btn_ActionBar = (Button) findViewById(R.id.btn_ActionBar);

        btn_ActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActionBarActivity.class));
            }
        });

    }

}
