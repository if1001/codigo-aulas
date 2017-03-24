package br.ufpe.cin.if1001.adapters;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActionBarActivity extends ListActivity {
    private ArrayList<String> palavras = null;
    private ArrayAdapter<String> adapter = null;
    //private ListView lista;
    private int qtdePalavrasIniciais = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_action_bar);
        //lista = (ListView) findViewById(R.id.lv_actionBar);
        inicializaAdapter();
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //informando que recurso utilizar para o menu
        getMenuInflater().inflate(R.menu.actionbar,menu);

        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        //encadeando chamada para superclasse
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.adicionar:
                adicionarPalavra();
                return true;
            case R.id.zerar:
                inicializaAdapter();
                return true;
            case R.id.sobre:
                Toast.makeText(this, R.string.sobre_toast, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializaAdapter() {
        palavras = new ArrayList<>();
        for (int i = 0; i < qtdePalavrasIniciais; i++) {
            palavras.add(Constants.listaLonga[i]);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, palavras);
        //lista.setAdapter(adapter);
        setListAdapter(adapter);
    }

    private void adicionarPalavra() {
        if (adapter.getCount() < Constants.listaLonga.length) {
            adapter.add(Constants.listaLonga[adapter.getCount()]);
        }
        else {
            Toast.makeText(this, "Acabou!", Toast.LENGTH_LONG).show();
        }
    }

}
