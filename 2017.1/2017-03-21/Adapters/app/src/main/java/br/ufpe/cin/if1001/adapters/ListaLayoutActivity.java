package br.ufpe.cin.if1001.adapters;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaLayoutActivity extends ListActivity {
    private TextView escolha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_layout);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Constants.listaLonga);
        setListAdapter(stringArrayAdapter);
        escolha = (TextView) findViewById(R.id.tv_listActivityLayout);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) l.getAdapter();
        String item = adapter.getItem(position);
        //String item = ((TextView) v).getText();
        //String item = Constants.listaLonga[position];
        escolha.setText(item);
    }
}
