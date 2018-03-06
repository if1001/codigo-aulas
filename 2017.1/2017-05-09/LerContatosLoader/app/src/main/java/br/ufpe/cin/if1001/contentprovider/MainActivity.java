package br.ufpe.cin.if1001.contentprovider;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	
	SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		String from[] = new String[]{ Contacts.DISPLAY_NAME };
		int to[] = new int[]{ R.id.contactName };
		adapter = new SimpleCursorAdapter(
				getApplicationContext(),
				R.layout.contact,
				null,
				from,
				to,
				0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);

    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	getLoaderManager().restartLoader(0, null, this);
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

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String columnsToExtract[] = new String[] { Contacts._ID, Contacts.DISPLAY_NAME };
		String whereClause = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND " +
							 "(" + Contacts.DISPLAY_NAME + " != '' ) AND " +
							 "(" + Contacts.STARRED + "== 1))";
		String sortOrder = Contacts.DISPLAY_NAME + " ASC";
		Log.v("IF1001", "onCreateLoader(...) ");
		
		return new CursorLoader(this, Contacts.CONTENT_URI, columnsToExtract, whereClause, null, sortOrder);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
		Log.v("IF1001", "onLoadFinished(...) "+data.getCount());
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
		Log.v("IF1001", "onLoaderReset() ");
	}
}
