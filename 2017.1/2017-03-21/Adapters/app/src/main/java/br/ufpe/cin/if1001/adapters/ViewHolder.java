package br.ufpe.cin.if1001.adapters;

import android.view.View;
import android.widget.TextView;

class ViewHolder {
    TextView login=null;

    ViewHolder(View row) {
        this.login=(TextView)row.findViewById(R.id.login);
    }
}