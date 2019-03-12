package br.ufpe.cin.if1001.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActResultActivity extends Activity {
    static final int PEGAR_CONTATO_REQ = 1;

    String telContato, nomeContato;

    TextView contato;
    Button getContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        contato = findViewById(R.id.contatoEscolhido);
        getContact = findViewById(R.id.btnContacts);

        getContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);//apenas contatos com telefone
                startActivityForResult(i, PEGAR_CONTATO_REQ);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PEGAR_CONTATO_REQ) {
            if (resultCode == RESULT_OK) {
                //Uri que aponta direto para um contato
                //content://contacts/1 -- que vai direto para um contato especifico
                Uri contactUri = data.getData();

                //pegar apenas nome e numero de telefone
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME};

                //fazendo query direto na thread principal...
                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // pega o numero de telefone
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                telContato = cursor.getString(column);
                column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME);
                nomeContato = cursor.getString(column);
                //altera textview
                contato.setText(nomeContato + " : " + telContato);
            }
        }
    }
}
