package br.ufpe.cin.if1001.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerViewChangeActivity extends Activity {

    RecyclerView recyclerView;
    private SortedList<Pessoa> pessoasList=null;
    private AddPessoaTask task=null;
    private PessoaAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //criando view
        recyclerView = new RecyclerView(this);

        //o tamanho do recycler view não é alterado pelo conteúdo (ocupa a tela inteira sempre)
        recyclerView.setHasFixedSize(true);

        //diferente de ListView e GridView, RecyclerView não sabe nada sobre como organizar elementos
        //esta tarefa eh delegada para o LayoutManager, possibilitando diferentes abordagens
        //neste caso, temos um layout linear para estruturar elementos verticalmente...
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //outras opcoes incluem GridLayoutManager, por ex.
        //tambem pode ser implementado um LayoutManager customizado

        adapter = new PessoaAdapter();

        //definindo o adapter (semelhante a listadapter...)
        recyclerView.setAdapter(adapter);

        //definindo layout da activity - sem usar XML (nao tem um ListActivity que possamos estender)
        setContentView(recyclerView);

        //construtor recebe a classe para objetos dentro da lista
        //objeto de callback que é chamado quando a lista é modificada por meio de add/insert/remove
        pessoasList = new SortedList<Pessoa>(Pessoa.class,sortCallback);

        task = new AddPessoaTask();
        task.execute();

    }


    //ponte entre SortedList e RecyclerView.Adapter
    //obviamente, como o nome sugere, ordena o conteudo

    //diferente de um add() de ArrayList, que coloca no fim da lista,
    // muda a forma de inserir, para manter ordem
    SortedList.Callback<Pessoa> sortCallback = new SortedList.Callback<Pessoa>() {
        //1. ajuda a definir como se dará a ordenação
        //2. passa informação de como ordenação é feita para o Adapter - animações 'for free'

        //implementação padrão de método de comparação
        //estamos terceirizando o compareTo de Strings, já existente
        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            return o1.getLogin().compareTo(o2.getLogin());
        }

        //delegando para o adapter...
        @Override
        public void onInserted(int position, int count) {
            adapter.notifyItemRangeInserted(position,count);
        }

        //delegando para o adapter...
        @Override
        public void onRemoved(int position, int count) {
            adapter.notifyItemRangeRemoved(position,count);
        }

        //delegando para o adapter...
        @Override
        public void onMoved(int fromPosition, int toPosition) {
            adapter.notifyItemMoved(fromPosition,toPosition);
        }

        //delegando para o adapter...
        @Override
        public void onChanged(int position, int count) {
            adapter.notifyItemRangeChanged(position,count);
        }

        //representação visual dos itens é a mesma? (reusar)
        @Override
        public boolean areContentsTheSame(Pessoa oldItem, Pessoa newItem) {
            return areItemsTheSame(oldItem,newItem);
        }

        //representação lógica dos itens é a mesma? neste caso, assumimos como login igual (compareTo)
        @Override
        public boolean areItemsTheSame(Pessoa item1, Pessoa item2) {
            return (compare(item1,item2)==0);
        }
    };

    private class PessoaAdapter extends RecyclerView.Adapter<CardPessoaHolder> {

        @Override
        public CardPessoaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //cria, configura e retorna um ViewHolder para uma linha da lista
            //parent é o ViewGroup que contem as views, usado pelo layout inflater
            //viewType é para o caso de ter múltiplos tipos de Views, em um RecyclerView

            View v = getLayoutInflater().inflate(R.layout.itemlistacardviewclick2,parent,false);
            return new CardPessoaHolder(v);
        }

        @Override
        public void onBindViewHolder(CardPessoaHolder holder, int position) {
            //responsavel por atualizar ViewHolder com dados de um elemento na posição 'position'
            //neste caso estamos pegando a referencia direta da lista como atributo da classe...
            // poderiamos ter passado como parametro do construtor, como nos exemplos anteriores
            holder.bindModel(pessoasList.get(position));
        }

        @Override
        public int getItemCount() {
            //total de elementos - direto do atributo
            return pessoasList.size();
        }
    }

    //responsavel por fazer o binding dos dados com widgets para cada linha da lista
    static class CardPessoaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome=null;
        TextView login=null;
        ImageView icone=null;
        Uri site = null;

        //poderia tambem passar algum objeto aqui construido no adapter, para nao adicionar atributos
        CardPessoaHolder(View row) {
            super(row);

            nome = (TextView) row.findViewById(R.id.nome);
            login = (TextView) row.findViewById(R.id.login);
            icone = (ImageView) row.findViewById(R.id.icone);

            //definindo listener para linha/card inteiro
            //poderia definir click listener para cada view (nome, login...)
            row.setOnClickListener(this);
        }

        void bindModel(Pessoa p) {
            nome.setText(p.getNome());
            login.setText(p.getLogin());
            site = Uri.parse(p.getSite());

            if (p.getLogin().equals("lmt")) {
                icone.setImageResource(R.drawable.ok);
            }
            else {
                icone.setImageResource(R.drawable.delete);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(v.getContext(), "Clicou no item da posição: "+position,Toast.LENGTH_SHORT).show();

            //v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,site));
        }
    }

    private class AddPessoaTask extends AsyncTask<Void,Pessoa,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (Pessoa p : Constants.maisPessoas) {
                if (isCancelled()) {
                    break;
                }
                publishProgress(p);
                SystemClock.sleep(500);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Pessoa... values) {
            if (!isCancelled()) {
                pessoasList.add(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Encerrado!", Toast.LENGTH_SHORT).show();
            task = null;
        }
    }
}
