package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends Activity {

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

    private class PessoaAdapter extends RecyclerView.Adapter<CardHolder> {
        Pessoa[] pessoas;

        PessoaAdapter(Pessoa[] pArray) {
            pessoas = pArray;
        }

        @Override
        public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CardHolder(getLayoutInflater().inflate(R.layout.itemlistacardview, parent, false));
        }

        @Override
        public void onBindViewHolder(CardHolder holder, int position) {
            holder.bindModel(pessoas[position]);
        }

        @Override
        public int getItemCount() {
            return pessoas.length;
        }
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        TextView nome = null;
        TextView login = null;
        ImageView icone = null;


        CardHolder(View row) {
            super(row);

            nome = row.findViewById(R.id.nome);
            login = row.findViewById(R.id.login);
            icone = row.findViewById(R.id.icone);

        }

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