package br.ufpe.cin.if1001.recyclerview;

import android.app.Activity;
import android.os.Bundle;
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
        recyclerView = new RecyclerView(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new PessoaAdapter(Constants.pessoas));

        //ItemDecoration permite adicionar dividers
        //nao suportado em targetSDKversion atual... (22+)
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        setContentView(recyclerView);

    }

    private class PessoaAdapter extends RecyclerView.Adapter<LinhaHolder> {

        Pessoa[] pessoas;

        PessoaAdapter(Pessoa[] ps) {
            pessoas = ps;
        }

        @Override
        public LinhaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.itemlista,parent,false);
            LinhaHolder lh = new LinhaHolder(v);
            return lh;
        }

        @Override
        public void onBindViewHolder(LinhaHolder holder, int position) {
            holder.bindModel(pessoas[position]);
        }

        @Override
        public int getItemCount() {
            return pessoas.length;
        }
    }

    static class LinhaHolder extends RecyclerView.ViewHolder {

        ImageView icone = null;
        TextView nome = null;
        TextView login = null;
        public LinhaHolder(View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.nome);
            login = (TextView) itemView.findViewById(R.id.login);
            icone = (ImageView) itemView.findViewById(R.id.icone);
        }

        void bindModel(Pessoa p) {
            nome.setText(p.getNome());
            login.setText(p.getLogin());
            //if (p.getLogin().equals("lmt")) {
                icone.setImageResource(R.drawable.ok);
            //}
            //else{
                //icone.setImageResource(R.drawable.delete);
            //}
        }
    }

}
