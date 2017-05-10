package br.ufpe.cin.if1001.contentconsumer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArrayProviderListActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_provider_list);
        ListView lv_pessoas = (ListView) findViewById(R.id.lv_Pessoas);
        ContentResolver cr = getContentResolver();
        //consulta na main thread, pode ser custoso, usar AsyncTask ou Loader
        Cursor c = cr.query(ContentProviderContract.CONTENT_AULA_URI, null, null, null, null);
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.itemlista,
                        c,
                        new String[] {ContentProviderContract.NOME},
                        new int[] {R.id.pNome},
                        0);
        lv_pessoas.setAdapter(adapter);

    }

}
