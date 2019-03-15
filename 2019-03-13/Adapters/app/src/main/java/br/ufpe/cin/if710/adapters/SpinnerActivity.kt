package br.ufpe.cin.if710.adapters

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {
    private var escolha: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val stringArrayAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                Constants.lista)

        escolha = findViewById(R.id.tv_SpinnerActivity)

        val spinner = findViewById<Spinner>(R.id.spinner)
        //Como vão se parecer os itens do dropdown
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = stringArrayAdapter

        //Ao invés de definirmos uma implementação anônima, a classe estende a interface associada
        // com o tipo esperado como argumento deste método.
        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        // o operador !! pode levar a null pointer exception
        escolha!!.text = (view as TextView).text
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        //não estou fazendo nada...
    }
}
