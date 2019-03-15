package br.ufpe.cin.if710.adapters

import android.app.ListActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapterActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pessoaAdapter = PessoaAdapter(this, Constants.maisPessoasAinda)
        listAdapter = pessoaAdapter
    }

    internal inner class PessoaAdapter(
            var c: Context,
            var pessoas: Array<Pessoa>) : BaseAdapter() {

        override fun getCount(): Int {
            return pessoas.size
        }

        override fun getItem(position: Int): Any {
            return pessoas[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val v = LayoutInflater.from(c).inflate(R.layout.item_custom_adapter, parent, false)

            //Alternativa seria:
            /*
            //View v;
            if( convertView == null) {
                v = LayoutInflater.from(c).inflate(R.layout.customadapter, parent, false);
            } else {
                v = convertView;
            }
            //ainda precisaria dar findViewById... (custoso)
            */


            //Buscando a referência ao TextView para inserirmos o nome
            val tvNome = v.findViewById<TextView>(R.id.nome)
            //Buscando a referência ao TextView para inserirmos o login
            val tvLogin : TextView = v.findViewById(R.id.login)

            //getItem retorna Object, então precisamos dar o cast
            // ou criar um método específico que retorne pessoa
            val nome = (getItem(position) as Pessoa).nome
            val login = (getItem(position) as Pessoa).login

            //Efetivamente setando o nome e login na View
            tvNome.text = nome
            tvLogin.text = "($login)"

            return v
        }
    }
}