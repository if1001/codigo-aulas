package br.ufpe.cin.if1001.adapters;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new PessoaAdapter());
    }

    class PessoaAdapter extends ArrayAdapter<Pessoa> {

        PessoaAdapter() {
            super(CustomAdapterActivity.this, R.layout.customadapter, R.id.nome, Constants.pessoas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View linha = super.getView(position, convertView, parent);
            TextView tvLogin = (TextView) linha.findViewById(R.id.login);
            String login = Constants.pessoas[position].getLogin();
            tvLogin.setText("(" + login +")");
            /**/
            return linha;
        }
    }


}
