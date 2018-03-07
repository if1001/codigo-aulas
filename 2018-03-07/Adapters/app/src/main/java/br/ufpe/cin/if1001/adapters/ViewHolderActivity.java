package br.ufpe.cin.if1001.adapters;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ViewHolderActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new PessoaAdapter(this, Constants.maisPessoas));
    }

    class PessoaAdapter extends BaseAdapter {
        private Context context;
        private Pessoa[] pessoas;

        public PessoaAdapter(Context c, Pessoa[] ps) {
            this.context = c;
            this.pessoas = ps;
        }

        @Override
        public int getCount() {
            return pessoas.length;
        }

        @Override
        public Object getItem(int position) {
            return pessoas[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            ViewHolder holder;

            if (convertView == null) {
                //Não está reusando, então precisa inflar o layout
                v = LayoutInflater.from(context).inflate(R.layout.customadapter, parent, false);
                //Inicializa novo ViewHolder, para armazenar referências aos IDs
                holder = new ViewHolder(v);
                //"Pendurando" o ViewHolder na View, permitindo acesso uma vez que tenhamos este objeto
                v.setTag(holder);
            } else {
                //já existe uma View a ser reutilizada
                v = convertView;
                //Basta recuperar o objeto ViewHolder associado com a Tag da View reutilizada
                holder = (ViewHolder) v.getTag();
            }

            Pessoa p = (Pessoa) getItem(position);
            //Setando valores por meio do holder
            holder.nome.setText(p.getNome());
            holder.login.setText(p.getEmail());

            return v;
        }
    }
}