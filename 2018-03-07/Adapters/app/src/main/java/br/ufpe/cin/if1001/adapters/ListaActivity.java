package br.ufpe.cin.if1001.adapters;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Não precisa usar setContentView... (o layout é criado automaticamente)

        //Um adapter faz a ponte entre uma coleção de dados e uma View em Android
        //Perceba que passamos, além do contexto e da coleção de dados em si, um layout.
        //Este layout é responsável por determinar como os itens da lista serão exibidos.
        //Neste exemplo estamos usando um layout já pré-definido pela plataforma, observe
        // que a referência é feita por meio de "android.R.layout...", ao invés de diretamente
        // usarmos "R.layout...". Nos próximos exemplos veremos como usar outros layouts.
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Constants.lista
        );


        //perceba que é um método nativo da classe, não estamos chamando set em um objeto
        setListAdapter(stringArrayAdapter);

        //Pegando a referência para o objeto ListView. Novamente, método nativo da classe
        ListView lv = getListView();

        //As duas chamadas abaixo poderiam ser feitas diretamente em getListView().set...
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                Toast.makeText(getApplicationContext(),tv.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
