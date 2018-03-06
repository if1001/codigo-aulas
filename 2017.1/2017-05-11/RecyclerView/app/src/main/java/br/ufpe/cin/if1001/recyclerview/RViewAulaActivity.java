package br.ufpe.cin.if1001.recyclerview;

import android.app.Activity;
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

public class RViewAulaActivity extends Activity {
    RecyclerView recyclerView;
    private SortedList<Pessoa> listaPessoas = null;
    private AddPessoaTask task = null;
    private PessoaAdapter adapter = null;


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

        listaPessoas = new SortedList<Pessoa>(Pessoa.class,metodosCallback);

        task = new AddPessoaTask();
        task.execute();
    }

    SortedList.Callback<Pessoa> metodosCallback = new SortedList.Callback<Pessoa>() {
        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            //return o1.getLogin().compareTo(o2.getLogin());
            return o1.getNome().compareTo(o2.getNome());
        }

        @Override
        public void onInserted(int position, int count) {
            adapter.notifyItemRangeInserted(position,count);
        }

        @Override
        public void onRemoved(int position, int count) {
            adapter.notifyItemRangeRemoved(position,count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            adapter.notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Pessoa oldItem, Pessoa newItem) {
            return areItemsTheSame(oldItem,newItem);
        }

        @Override
        public boolean areItemsTheSame(Pessoa item1, Pessoa item2) {
            return compare(item1,item2)==0;
        }
    };

    private class PessoaAdapter extends RecyclerView.Adapter<CardChangeHolder> {

        @Override
        public CardChangeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //cria, configura e retorna um ViewHolder para uma linha da lista
            //parent é o ViewGroup que contem as views, usado pelo layout inflater
            //viewType é para o caso de ter múltiplos tipos de Views, em um RecyclerView
            //View v = getLayoutInflater().inflate(R.layout.itemlistacardview,parent,false);
            //View v = getLayoutInflater().inflate(R.layout.itemlistacardviewclick,parent,false);
            View v = getLayoutInflater().inflate(R.layout.itemlistacardviewclick2,parent,false);
            return new CardChangeHolder(v);
        }

        @Override
        public void onBindViewHolder(CardChangeHolder holder, int position) {
            //responsavel por atualizar ViewHolder com dados de um elemento na posição 'position'
            holder.bindModel(listaPessoas.get(position));
        }

        @Override
        public int getItemCount() {
            //total de elementos
            return listaPessoas.size();
        }
    }
    static class CardChangeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome=null;
        TextView login=null;
        ImageView icone=null;
        Uri site = null;

        //poderia tambem passar algum objeto aqui construido no adapter, para nao adicionar atributos
        CardChangeHolder(View row) {
            super(row);

            nome = (TextView) row.findViewById(R.id.nome);
            login = (TextView) row.findViewById(R.id.login);
            icone = (ImageView) row.findViewById(R.id.icone);

            //definindo listener para linha/card inteiro
            //poderia definir click listener para cada view (nome, login...)
            icone.setOnClickListener(this);
        }

        void bindModel(Pessoa p) {
            nome.setText(p.getNome());
            login.setText(p.getLogin());
            site = Uri.parse(p.getSite());

            //if (p.getLogin().equals("lmt")) {
              //  icone.setImageResource(R.drawable.ok);
            //}
            //else {
                icone.setImageResource(R.drawable.delete);
            //}
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(v.getContext(), "Clicou no item da posição: "+position,Toast.LENGTH_SHORT).show();

            //Intent i = new Intent(Intent.ACTION_VIEW,site);
            //v.getContext().startActivity(i);
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
                SystemClock.sleep(1000);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Pessoa... values) {
            if (!isCancelled()) {
                listaPessoas.add(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(),"Terminou!", Toast.LENGTH_SHORT).show();
            task = null;
        }
    }

}
