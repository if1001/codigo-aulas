package br.ufpe.cin.if710.adapters

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        /*
        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = PessoaAdapter(Constants.maisPessoas, this)
        listRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        */

        listRecyclerView.apply {
            //Diferente de ListView e GridView, RecyclerView não sabe nada sobre como organizar elementos.
            //Esta tarefa é delegada para o LayoutManager, possibilitando diferentes abordagens.
            //Neste caso, temos um layout linear para estruturar elementos verticalmente...
            //Outras opções incluem GridLayoutManager, por exemplo, como veremos em outra Activity
            //Também pode ser implementado um LayoutManager customizado.
            layoutManager = LinearLayoutManager(applicationContext)

            //Definindo o adapter - aqui não tem muita diferença de ListView
            adapter = PessoaAdapter(Constants.maisPessoas, applicationContext)

            //ItemDecoration permite adicionar dividers
            //Só é suportado a partir de targetSDKversion 22+
            addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        }

    }
}
