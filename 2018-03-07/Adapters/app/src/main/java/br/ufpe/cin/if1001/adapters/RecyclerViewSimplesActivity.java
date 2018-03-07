package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewSimplesActivity extends Activity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Não estamos usando um layout XML, como de praxe. Nem tampouco estendemos de outra classe.
        //Vamos criar a View programaticamente e setar mais abaixo

        //Criando view - RecyclerView
        recyclerView = new RecyclerView(this);

        //O tamanho do recycler view não é alterado pelo conteúdo (ocupa a tela inteira sempre)
        recyclerView.setHasFixedSize(true);

        //Diferente de ListView e GridView, RecyclerView não sabe nada sobre como organizar elementos.
        //Esta tarefa é delegada para o LayoutManager, possibilitando diferentes abordagens.
        //Neste caso, temos um layout linear para estruturar elementos verticalmente...
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Outras opções incluem GridLayoutManager, por exemplo, como veremos em outra Activity
        //Também pode ser implementado um LayoutManager customizado.

        //Definindo o adapter - aqui não tem muita diferença de ListView
        recyclerView.setAdapter(new PessoaAdapter(Constants.pessoas));

        //ItemDecoration permite adicionar dividers
        //Só é suportado a partir de targetSDKversion 22+
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //setContentView sendo chamado direto com um objeto View, ao invés de um layout xml
        //Definindo layout da activity sem usar XML (nao tem um ListActivity que possamos estender)
        setContentView(recyclerView);

    }

    private class PessoaAdapter extends RecyclerView.Adapter<LinhaHolder> {

        //fonte de dados
        Pessoa[] pessoas;

        //instanciando fonte de dados
        PessoaAdapter(Pessoa[] ps) {
            pessoas = ps;
        }

        @Override
        public LinhaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Cria, configura e retorna um ViewHolder para uma linha da lista.
            //parent é o ViewGroup que contem as views, usado pelo layout inflater
            //viewType é para o caso de ter múltiplos tipos de Views, em um RecyclerView
            View v = getLayoutInflater().inflate(R.layout.itemlista_pessoa, parent, false);
            //O layout está sendo inflado pois este método é chamado ao CRIAR o ViewHolder

            //Após inflar o layout passa como argumento para o construtor LinhaHolder, que efetua
            // as buscas pelos IDs.
            LinhaHolder lh = new LinhaHolder(v);

            return lh;
        }

        @Override
        public void onBindViewHolder(LinhaHolder holder, int position) {
            //Responsável por atualizar ViewHolder com dados de um elemento na posição 'position'
            holder.bindModel(pessoas[position]);
            //Poderia dar setText diretamente aqui, caso os atributos sejam acessíveis...
        }

        @Override
        public int getItemCount() {
            //Total de elementos
            return pessoas.length;
        }
    }

    //Responsável por fazer o binding dos dados com widgets para cada linha da lista
    // Mesma ideia do padrão ViewHolder usado para ListView.
    // No entanto, em RecyclerView, isto não é opcional.
    static class LinhaHolder extends RecyclerView.ViewHolder {

        ImageView icone = null;
        TextView nome = null;
        TextView login = null;

        //Construtor precisa receber uma View
        public LinhaHolder(View itemView) {
            super(itemView);

            //já faz o findViewById no momento da criação, igual a classe ViewHolder.java
            nome = itemView.findViewById(R.id.nome);
            login = itemView.findViewById(R.id.login);
            icone = itemView.findViewById(R.id.icone);
        }

        //Recebe um objeto Pessoa e atualiza as Views de forma correspondente
        void bindModel(Pessoa p) {
            nome.setText(p.getNome());
            login.setText(p.getLogin());
            if (p.getLogin().equals("lmt")) {
                icone.setImageResource(R.drawable.ok);
            } else {
                icone.setImageResource(R.drawable.delete);
            }
        }
    }

}
