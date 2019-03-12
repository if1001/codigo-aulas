package br.ufpe.cin.if710.datamanagement;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionarEstadoActivity extends Activity {
    private SQLEstadosHelper db = null;
    EditText UF, nomeEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_estado);
        //Pegando instância do banco de dados
        db = SQLEstadosHelper.getInstance(this);

        UF = findViewById(R.id.txtUF);
        nomeEstado = findViewById(R.id.txtNome);

        Button b = findViewById(R.id.btnInserirEstado);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Roda a Task de Inserir Estado, passando o que foi digitado nos campos de texto como argumento
                String estadoUF = UF.getText().toString();
                String estadoNOME = nomeEstado.getText().toString();
                new InsereTask().execute(estadoUF,estadoNOME);
            }
        });
    }

    private class InsereTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Montando objeto ContentValues com UF e Nome do Estado
            ContentValues cv = new ContentValues();
            cv.put(SQLEstadosHelper.STATE_CODE,params[0]);
            cv.put(SQLEstadosHelper.STATE_NAME,params[1]);
            //Roda a inserção no banco de dados, usando os parâmetros passados
            //INSERT INTO estados VALUES ('PE','Pernambuco');
            db.getWritableDatabase().insert(SQLEstadosHelper.DATABASE_TABLE,null,cv);
            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            //Assim que terminar o doInBackground, feche a Activity
            finish();
        }
    }
    /**/
}
