package br.ufpe.cin.if710.adapters

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view.*
import java.util.*

class ListViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        // int -> String[]
        val opcoes = hashMapOf(
                0 to Constants.lista,
                1 to Constants.listaLonga,
                2 to Constants.palavrasSimilares
        )

        btn_Troca.setOnClickListener{
            val i = Random().nextInt(opcoes.size)
            listaElementos.adapter =
                    ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    opcoes[i])

        }

        listaElementos.setOnItemClickListener{
            parent, _, position, id ->
            val texto = parent.adapter.getItem(position)
            val t = Toast.makeText(applicationContext,texto.toString(),Toast.LENGTH_SHORT)
            t.show()
        }
    }
}
