package br.ufpe.cin.if1001.threads;

import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RecyclerViewActivity extends Activity {
    RecyclerView recyclerView;
    private AddPessoaTask task = null;
    private PessoaAdapter adapter = null;
    SortedList<Pessoa> listaPessoas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        recyclerView = new RecyclerView(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaPessoas = new SortedList<Pessoa>(Pessoa.class,metodosCallback);
        adapter = new PessoaAdapter(listaPessoas);
        recyclerView.setAdapter(adapter);

        setContentView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        task = new AddPessoaTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (task!=null) {
            task.execute(Constants.maisPessoas);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (task!=null) {
            task.cancel(true);
        }
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

        private SortedList<Pessoa> pessoas;
        public PessoaAdapter(SortedList<Pessoa> ps) {
            this.pessoas = ps;
        }

        @Override
        public CardChangeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.pessoa_cardview,parent,false);
            return new CardChangeHolder(v);
        }

        @Override
        public void onBindViewHolder(CardChangeHolder holder, int position) {
            holder.bindModel(this.pessoas.get(position));
        }

        @Override
        public int getItemCount() {
            return this.pessoas.size();
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

            nome = row.findViewById(R.id.nome);
            login = row.findViewById(R.id.login);
            icone = row.findViewById(R.id.icone);

            icone.setOnClickListener(this);
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

        }
    }

    private class AddPessoaTask extends AsyncTask<Pessoa,Pessoa,Void> {

        @Override
        protected Void doInBackground(Pessoa... params) {
            for (Pessoa p : params) {
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