package br.ufpe.cin.if1001.adapters;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ViewHolderActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new PessoaAdapter());
    }

    private Pessoa getModel(int position) {
        return(((PessoaAdapter)getListAdapter()).getItem(position));
    }

    class PessoaAdapter extends ArrayAdapter<Pessoa> {

        PessoaAdapter() {
            super(ViewHolderActivity.this, R.layout.customadapter, R.id.nome, Constants.pessoas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View linha = super.getView(position, convertView, parent);
            ViewHolder holder = (ViewHolder) linha.getTag();
            if (holder == null) {
                holder = new ViewHolder(linha);
                linha.setTag(holder);
            }

            String login = Constants.pessoas[position].getLogin();
            holder.login.setText(login);

            /**/
            return linha;
        }

    }

}
