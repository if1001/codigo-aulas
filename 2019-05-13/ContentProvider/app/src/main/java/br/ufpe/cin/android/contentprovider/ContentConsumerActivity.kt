package br.ufpe.cin.android.contentprovider

import android.app.Activity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_content_consumer.*

class ContentConsumerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_consumer)

        val cr = contentResolver
        //consulta na main thread, pode ser custoso, usar AsyncTask ou Loader
        val c = cr.query(
            ContentProviderContract.CONTENT_LIST_URI,
            null, null, null, null)
        val adapter = SimpleCursorAdapter(
            this,
            R.layout.itemlista,
            c,
            arrayOf(ContentProviderContract.NOME),
            intArrayOf(R.id.pNome),
            0
        )
        lv_Pessoas.adapter = adapter
    }
}
