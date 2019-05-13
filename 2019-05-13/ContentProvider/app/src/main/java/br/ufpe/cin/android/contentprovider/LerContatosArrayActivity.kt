package br.ufpe.cin.android.contentprovider

import android.app.ListActivity
import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter

import java.util.ArrayList

class LerContatosArrayActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentResolver = getContentResolver()
        val columns = arrayOf<String>(ContactsContract.Contacts.DISPLAY_NAME)
        //se nao tem permissao vai dar runtime exception
        val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                columns,
                null, null, null)
        Log.v("IF710", "QTDE: " + cursor.getCount())

        val contacts = ArrayList<String>()

        if (cursor.moveToFirst()) {
            do {
                contacts.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
            } while (cursor.moveToNext())
        }
        val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                contacts)

        setListAdapter(adapter)
    }
}
