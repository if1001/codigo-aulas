package br.ufpe.cin.if710.contentconsumer

import android.app.ListActivity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.SimpleCursorAdapter

class LerContatosLoaderActivity : ListActivity(), LoaderManager.LoaderCallbacks<Cursor> {
    internal var adapter: SimpleCursorAdapter


    @Override
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        val from = arrayOf<String>(ContactsContract.Contacts.DISPLAY_NAME)
        val to = intArrayOf(R.id.contactName)
        adapter = SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.contact, null,
                from,
                to,
                0)
        setListAdapter(adapter)
        getLoaderManager().initLoader(0, null, this)

    }

    @Override
    protected fun onResume() {
        super.onResume()
        getLoaderManager().restartLoader(0, null, this)
    }

    @Override
    fun onCreateLoader(id: Int, args: Bundle): Loader<Cursor> {
        val columnsToExtract = arrayOf<String>(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
        val whereClause = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND " +
                "(" + ContactsContract.Contacts.DISPLAY_NAME + " != '' ) AND " +
                "(" + ContactsContract.Contacts.STARRED + "== 1))"
        val sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        Log.v("IF710", "onCreateLoader(...) ")

        //se nao tem permissao vai dar runtime exception
        return CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, columnsToExtract, whereClause, null, sortOrder)
    }

    @Override
    fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
        adapter.swapCursor(data)
        Log.v("IF710", "onLoadFinished(...) " + data.getCount())
    }

    @Override
    fun onLoaderReset(loader: Loader<Cursor>) {
        adapter.swapCursor(null)
        Log.v("IF710", "onLoaderReset() ")
    }
}
