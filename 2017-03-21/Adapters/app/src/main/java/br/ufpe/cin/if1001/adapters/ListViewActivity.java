package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {
    private TextView escolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                R.layout.itemlista,
                Constants.lista);
        ArrayAdapter<Pessoa> pessoaArrayAdapter= new ArrayAdapter<>(this,
                R.layout.itemlista,
                Constants.pessoas);

        escolha = (TextView) findViewById(R.id.tv_listViewActivity);

        ListView lv = (ListView) findViewById(R.id.lv_Elementos);
        lv.setAdapter(pessoaArrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ArrayAdapter<Pessoa> arrayAdapter = (ArrayAdapter<Pessoa>) listView.getAdapter();
                Pessoa p = arrayAdapter.getItem(position);
                escolha.setText(p.getLogin());
            }
        });
    }
}
