package br.ufpe.cin.if710.adapters

import android.view.View
import android.widget.TextView

internal class ViewHolder(row: View) {
    var nome: TextView? = null
    var login: TextView? = null

    init {
        this.nome = row.findViewById(R.id.nome)
        this.login = row.findViewById(R.id.login)
    }
}