package br.ufpe.cin.if1001.contentprovider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //nao tem setContentView
		ContentResolver contentResolver = getContentResolver();
		
		String columnsToExtract[] = new String[] { Contacts._ID, Contacts.DISPLAY_NAME };
		String whereClause = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND " +
							 "(" + Contacts.DISPLAY_NAME + " != '' ) AND " +
							 "(" + Contacts.STARRED + "== 1))";
		String sortOrder = Contacts._ID + " ASC";
		sortOrder = Contacts.DISPLAY_NAME + " ASC";

		
		Cursor cursor = contentResolver.query(
                Contacts.CONTENT_URI,
                columnsToExtract,
                whereClause,
                null,
                sortOrder);
		
		String from[] = new String[]{ Contacts.DISPLAY_NAME };
		int to[] = new int[]{ R.id.contactName };
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.contact,
                cursor,
                from,
                to,
                0);
		
		Log.v("IF1001", "QTDE: "+cursor.getCount());
		setListAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
