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
        //Definindo um layout xml específico
        //É necessário que este layout tenha uma ListView com android:id="@android:id/list"
        setContentView(R.layout.activity_lista_layout);

        //Estamos usando dados provenientes de strings.xml.
        // Poderíamos adicionar o atributo direto no XML:
        // android:entries="@array/estadosBrasil"
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,//ou getApplicationContext
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.estadosBrasil));

        setListAdapter(stringArrayAdapter);
        escolha = findViewById(R.id.tv_listActivityLayout);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //pegando referência ao adapter, por meio do objeto l
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) l.getAdapter();
        //acessando item específico na lista contida no adapter, de acordo com position
        String item = adapter.getItem(position);
        escolha.setText(item);
    }
}
