package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardViewClickActivity extends Activity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PessoaAdapter(Constants.maisPessoas));
        setContentView(recyclerView);

    }

    private class PessoaAdapter extends RecyclerView.Adapter<CardClickHolder> {
        Pessoa[] pessoas;

        PessoaAdapter(Pessoa[] pArray) {
            pessoas = pArray;
        }

        @Override
        public CardClickHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //View v = getLayoutInflater().inflate(R.layout.itemlistacardview,parent,false);
            //View v = getLayoutInflater().inflate(R.layout.itemlistacardviewclick,parent,false);
            View v = getLayoutInflater().inflate(R.layout.itemlistacardviewclick2, parent, false);
            return new CardClickHolder(v);
        }

        @Override
        public void onBindViewHolder(CardClickHolder holder, int position) {
            holder.bindModel(pessoas[position]);
        }

        @Override
        public int getItemCount() {
            return pessoas.length;
        }
    }

    static class CardClickHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome = null;
        TextView login = null;
        ImageView icone = null;
        Uri site = null;

        //poderia tambem passar algum objeto aqui construido no adapter, para nao adicionar atributos
        CardClickHolder(View row) {
            super(row);

            nome = row.findViewById(R.id.nome);
            login = row.findViewById(R.id.login);
            icone = row.findViewById(R.id.icone);

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
            } else {
                icone.setImageResource(R.drawable.delete);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(v.getContext(), "Clicou no item da posição: " + position, Toast.LENGTH_SHORT).show();

            //Intent i = new Intent(Intent.ACTION_VIEW,site);
            //v.getContext().startActivity(i);
        }
    }
}
