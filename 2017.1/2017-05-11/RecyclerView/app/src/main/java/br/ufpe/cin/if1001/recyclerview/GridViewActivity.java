package br.ufpe.cin.if1001.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewActivity extends Activity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //criando view
        recyclerView = new RecyclerView(this);

        //o tamanho do recycler view não é alterado pelo conteúdo (ocupa a tela inteira sempre)
        recyclerView.setHasFixedSize(true);

        //neste caso, temos um layout em grid para estruturar elementos de acordo com spans...
        //spans neste caso seriam colunas
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //definindo o adapter (semelhante a listadapter...)
        recyclerView.setAdapter(new PessoaAdapter(Constants.maisPessoas));

        //definindo layout da activity - sem usar XML (nao tem um ListActivity que possamos estender)
        setContentView(recyclerView);

    }

    private class PessoaAdapter extends RecyclerView.Adapter<GridHolder> {
        //fonte de dados
        Pessoa[] pessoas;

        //instanciando fonte de dados
        PessoaAdapter(Pessoa[] pArray) {
            pessoas = pArray;
        }

        @Override
        public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //cria, configura e retorna um ViewHolder para uma linha da lista
            //parent é o ViewGroup que contem as views, usado pelo layout inflater
            //viewType é para o caso de ter múltiplos tipos de Views, em um RecyclerView

            return new GridHolder(getLayoutInflater().inflate(R.layout.itemlistacardviewclick2,parent,false));
        }

        @Override
        public void onBindViewHolder(GridHolder holder, int position) {
            //responsavel por atualizar ViewHolder com dados de um elemento na posição 'position'
            holder.bindModel(pessoas[position]);
        }

        @Override
        public int getItemCount() {
            //total de elementos
            return pessoas.length;
        }
    }

    //responsavel por fazer o binding dos dados com widgets para cada linha da lista
    static class GridHolder extends RecyclerView.ViewHolder {
        TextView nome=null;
        TextView login=null;
        ImageView icone=null;


        GridHolder(View row) {
            super(row);

            nome = (TextView) row.findViewById(R.id.nome);
            login = (TextView) row.findViewById(R.id.login);
            icone = (ImageView) row.findViewById(R.id.icone);

        }

        void bindModel(Pessoa p) {
            nome.setText(p.getNome());
            login.setText(p.getLogin());

            if (p.getLogin().equals("lmt")) {
                icone.setImageResource(R.drawable.ok);
            }
            else {
                icone.setImageResource(R.drawable.delete);
            }
        }
    }}
