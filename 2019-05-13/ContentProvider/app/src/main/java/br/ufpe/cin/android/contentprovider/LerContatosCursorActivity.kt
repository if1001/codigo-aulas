package br.ufpe.cin.android.contentprovider

import android.app.ListActivity
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.SimpleCursorAdapter

class LerContatosCursorActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //nao tem setContentView
        val contentResolver = getContentResolver()
        val contactsURI = ContactsContract.Contacts.CONTENT_URI
        val colunas = arrayOf<String>(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)

        val where = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND " +
                "(" + ContactsContract.Contacts.DISPLAY_NAME + " != '' ) AND " +
                "(" + ContactsContract.Contacts.STARRED + "== 1))"
        val whereArgs: Array<String>? = null
        val sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC"

        //se nao tem permissao vai dar runtime exception
        val c = contentResolver.query(
                contactsURI, colunas,
                where,
                whereArgs,
                sortOrder
        )

        val from = arrayOf<String>(ContactsContract.Contacts.DISPLAY_NAME)
        val to = intArrayOf(R.id.contactName)

        val adapter = SimpleCursorAdapter(
                this, R.layout.contact, c, from, to, 0)

        setListAdapter(adapter)
    }
}
