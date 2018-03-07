package br.ufpe.cin.if1001.adapters;

import android.view.View;
import android.widget.TextView;

class ViewHolder {
    TextView nome = null;
    TextView login = null;

    ViewHolder(View row) {
        this.nome = row.findViewById(R.id.nome);
        this.login = row.findViewById(R.id.login);
    }
}