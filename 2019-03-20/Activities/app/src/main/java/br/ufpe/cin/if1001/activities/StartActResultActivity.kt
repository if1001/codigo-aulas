package br.ufpe.cin.if1001.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import kotlinx.android.synthetic.main.activity_results.*

class StartActResultActivity : Activity() {
    private val PEGAR_CONTATO_REQ = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        btnContacts.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"))
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE//apenas contatos com telefone
            startActivityForResult(i, PEGAR_CONTATO_REQ)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == PEGAR_CONTATO_REQ) {
            if (resultCode == Activity.RESULT_OK) {
                //Uri que aponta direto para um contato
                //content://contacts/1 -- que vai direto para um contato especifico
                val contactUri = data.data

                //pegar apenas nome e numero de telefone
                val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)

                //fazendo query direto na thread principal...
                val cursor = contentResolver.query(contactUri!!, projection, null, null, null)
                cursor!!.moveToFirst()

                // pega o numero de telefone
                var column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val telContato = cursor.getString(column)
                column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)
                val nomeContato = cursor.getString(column)
                //altera textview
                contatoEscolhido.text = "$nomeContato : $telContato"
            }
        }
    }
}
