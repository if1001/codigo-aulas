package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

        //ArrayAdapter é classe genérica, pode ser usado com qualquer tipo de objeto
        //O comportamento padrão deste adapter é chamar o toString do tipo passado como parâmetro
        //Neste caso, o método toString definido na classe Pessoa é quem determina
        // o conteúdo de um item da lista
        //Observe que neste caso estamos usando um layout definido dentro do projeto.
        //Como R.layout.itemlista consiste apenas de um TextView, a plataforma mapeia automaticamente
        //o valor obtido a partir do toString para constituir o conteúdo deste TextView.
        ArrayAdapter<Pessoa> pessoaArrayAdapter= new ArrayAdapter<>(this,
                R.layout.itemlista,
                Constants.pessoas);

        escolha = findViewById(R.id.tv_listViewActivity);

        ListView lv = findViewById(R.id.lv_Elementos);
        //pra exibir o texto na tela só precisa disso
        lv.setAdapter(pessoaArrayAdapter);




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //A assinatura do método onItemClick especifica que parent é um objeto
                // do tipo AdapterView<?>. Isto significa que este método não necessariamente
                // está atrelado a uma ListView. Por isso é necessário fazer o cast abaixo.
                // Não estamos testando, pois sabemos neste caso que é uma ListView.
                ListView listView = (ListView) parent;
                //A partir do parent, podemos obter o Adapter associado. Neste caso, novamente
                // é necessário fazer cast, pois getAdapter não sabe o tipo específico do objeto.
                // Até agora só vimos ArrayAdapter, mas existem outros tipos de Adapter, e também
                // é possível criar adapters personalizados.
                ArrayAdapter<Pessoa> arrayAdapter = (ArrayAdapter<Pessoa>) listView.getAdapter();
                //O adapter guarda objetos do tipo Pessoa, de acordo com o parâmetro de tipo.
                Pessoa p = arrayAdapter.getItem(position);
                //Tendo acesso a um objeto Pessoa, podemos chamar qualquer método disponível.
                escolha.setText(p.getSite());

                String site = p.getSite();
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(site));
                startActivity(i);
            }
        });
    }
}
