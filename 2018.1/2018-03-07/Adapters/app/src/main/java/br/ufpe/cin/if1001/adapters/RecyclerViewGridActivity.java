package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewGridActivity extends Activity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = new RecyclerView(this);
        recyclerView.setHasFixedSize(true);

        //Neste caso, temos um layout em grid para estruturar elementos de acordo com spans...
        //spans neste caso seriam colunas, portanto, funciona como grid
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(new PessoaAdapter(Constants.maisPessoas));
        setContentView(recyclerView);

    }

    private class PessoaAdapter extends RecyclerView.Adapter<GridHolder> {
        Pessoa[] pessoas;

        PessoaAdapter(Pessoa[] pArray) {
            pessoas = pArray;
        }

        @Override
        public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GridHolder(getLayoutInflater().inflate(R.layout.itemlistacardviewclick2, parent, false));
        }

        @Override
        public void onBindViewHolder(GridHolder holder, int position) {
            holder.bindModel(pessoas[position]);
        }

        @Override
        public int getItemCount() {
            return pessoas.length;
        }
    }

    static class GridHolder extends RecyclerView.ViewHolder {
        TextView nome = null;
        TextView login = null;
        ImageView icone = null;


        GridHolder(View row) {
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