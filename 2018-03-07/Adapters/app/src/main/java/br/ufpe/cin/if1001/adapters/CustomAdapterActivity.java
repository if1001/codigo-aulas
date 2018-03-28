package br.ufpe.cin.if1001.adapters;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapterActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PessoaAdapter pessoaAdapter = new PessoaAdapter(this, Constants.maisPessoasAinda);
        setListAdapter(pessoaAdapter);
    }

    class PessoaAdapter extends BaseAdapter {

        Context c;
        Pessoa[] pessoas;

        public PessoaAdapter(Context c, Pessoa[] ps) {
            this.c = c;
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
            View v = LayoutInflater.
                    from(c).
                    inflate(R.layout.customadapter, parent, false);

            //Alternativa seria:
            /*
            //View v;
            if( convertView == null) {
                v = LayoutInflater.from(c).inflate(R.layout.customadapter, parent, false);
            } else {
                v = convertView;
            }
            //ainda precisaria dar findViewById... (custoso)
            /**/


            //Buscando a referência ao TextView para inserirmos o nome
            TextView tvNome = v.findViewById(R.id.nome);
            //Buscando a referência ao TextView para inserirmos o login
            TextView tvLogin = v.findViewById(R.id.login);

            //getItem retorna Object, então precisamos dar o cast
            // ou criar um método específico que retorne pessoa
            String nome = ((Pessoa) getItem(position)).getNome();
            String login = ((Pessoa) getItem(position)).getLogin();

            //Efetivamente setando o nome e login na View
            tvNome.setText(nome);
            tvLogin.setText("(" + login +")");

            return v;
        }
    }

}
