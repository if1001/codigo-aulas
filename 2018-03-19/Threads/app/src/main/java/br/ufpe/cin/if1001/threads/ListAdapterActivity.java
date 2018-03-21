package br.ufpe.cin.if1001.threads;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterActivity extends Activity {
    AdicionarPessoaTask task;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adapter);

        List<Pessoa> pessoas = new ArrayList<>();
        lv = findViewById(R.id.lv_pessoas);

        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pessoas
        );

        lv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        task = new AdicionarPessoaTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        task.execute(Constants.maisPessoas);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (task!=null) {
            task.cancel(true);
        }
    }
    /**/

    class AdicionarPessoaTask extends AsyncTask<Pessoa,Pessoa,Void> {

        @Override
        protected Void doInBackground(Pessoa... pessoas) {
            for (Pessoa p : pessoas) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(p);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Pessoa... values) {
            ArrayAdapter<Pessoa> a = (ArrayAdapter<Pessoa>) lv.getAdapter();
            a.add(values[0]);
        }
    }
}
