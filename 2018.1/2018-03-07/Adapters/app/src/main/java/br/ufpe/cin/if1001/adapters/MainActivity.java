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

        //Pega referência do botão
        Button btn_ListActivity = findViewById(R.id.btn_ListActivity);
        //adiciona ação associada ao clique
        btn_ListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia outra Activity de forma explícita, estamos informando uma activity
                // em particular que desejamos iniciar
                startActivity(new Intent(getApplicationContext(),ListaActivity.class));
            }
        });

        //O restante do código abaixo segue a mesma sistemática, pega ref. ao botão e associa um
        // listener para o clique, disparando uma nova Activity
        Button btn_ListActivityLayout = findViewById(R.id.btn_ListActivityLayout);
        btn_ListActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListaLayoutActivity.class));
            }
        });

        Button btn_ListView = findViewById(R.id.btn_ListView);
        btn_ListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListViewActivity.class));
            }
        });

        Button btn_Spinner = findViewById(R.id.btn_Spinner);
        btn_Spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SpinnerActivity.class));
            }
        });

        Button btn_GridView = findViewById(R.id.btn_GridView);
        btn_GridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GridViewActivity.class));
            }
        });

        Button btn_AutoCompleteTextView = findViewById(R.id.btn_AutoCompleteTextView);
        btn_AutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AutoCompleteActivity.class));
            }
        });

        Button btn_CustomAdapter = findViewById(R.id.btn_CustomAdapter);
        btn_CustomAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CustomAdapterActivity.class));
            }
        });

        Button btn_ViewHolder = findViewById(R.id.btn_ViewHolder);

        btn_ViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewHolderActivity.class));
            }
        });

        Button btn_RView1 = findViewById(R.id.btn_RView1);
        btn_RView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RecyclerViewSimplesActivity.class));
            }
        });

        Button btn_CView1 = findViewById(R.id.btn_CView1);
        btn_CView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CardViewActivity.class));
            }
        });

        Button btn_CView2 = findViewById(R.id.btn_CView2);
        btn_CView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CardViewClickActivity.class));
            }
        });

        Button btn_CViewGrid = findViewById(R.id.btn_CViewGrid);
        btn_CViewGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RecyclerViewGridActivity.class));
            }
        });

    }

}
