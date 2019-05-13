package br.ufpe.cin.android.contentprovider

import android.app.Activity
import android.app.ListActivity
import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.widget.SimpleCursorAdapter

class ContentConsumerSQLiteActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cr = contentResolver
        //consulta na main thread, pode ser custoso, usar AsyncTask ou Loader
        val c = cr.query(ContentProviderContract.CONTENT_ESTADOS_URI, null, null, null, null)
        val adapter = SimpleCursorAdapter(
            this,
            R.layout.itemlista,
            c,
            arrayOf(ContentProviderContract.STATE_NAME, ContentProviderContract.STATE_CODE),
            intArrayOf(R.id.pNome, R.id.pEmail),
            0
        )
        listAdapter = adapter
    }
}
