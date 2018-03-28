package br.ufpe.cin.if710.datamanagement;

import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarEstadoActivity extends Activity {
    private SQLEstadosHelper db = null;
    EditText UF, nomeEstado;
    Button editar;
    String codigoEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_estado);

        //Pega objeto Intent usado para abrir a Activity
        Intent i = getIntent();
        //Pega o código do estado a ser editado
        codigoEstado = i.getStringExtra(SQLEstadosHelper.STATE_CODE);

        //Instância do Banco de Dados
        db = SQLEstadosHelper.getInstance(this);

        UF = findViewById(R.id.txtUF);
        nomeEstado = findViewById(R.id.txtNome);
        editar = findViewById(R.id.btnInserirEstado);

        //Roda uma Task para pegar os dados associados ao estado a ser editado
        new CarregaTask().execute(codigoEstado);
    }


    private class CarregaTask extends AsyncTask<String, Void, Cursor> {
        @Override
        protected Cursor doInBackground(String... params) {
            //Montando a consulta
            String selection = SQLEstadosHelper.STATE_CODE + " = ?";
            String[] selectionArgs = { params[0] };

            //Aqui não vai retornar todos os estados, mas sim apenas aquele
            // que está associado com o código passado como argumento
            Cursor result =
                    db.getReadableDatabase()
                            .query(SQLEstadosHelper.DATABASE_TABLE,
                                    SQLEstadosHelper.columns,
                                    selection,
                                    selectionArgs,
                                    null,
                                    null,
                                    null);

            result.getCount();

            return result;
        }

        @Override
        public void onPostExecute(Cursor result) {
            //Se estiver tudo ok com a consulta, tem algum resultado ao menos
            if ( result.moveToFirst()) {
                //Atualiza os campos de texto para os valores de código e estado
                UF.setText(result.getString(result.getColumnIndex(SQLEstadosHelper.STATE_CODE)));
                nomeEstado.setText(result.getString(result.getColumnIndex(SQLEstadosHelper.STATE_NAME)));
                //Associa um listener para o botão de editar
                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Ao clicar, roda a task que edita, passando os valores digitados
                        new EditaTask().execute(UF.getText().toString(), nomeEstado.getText().toString());
                    }
                });
            }
            else {
                //Em caso de problemas, encerra a Activity
                Toast.makeText(getApplicationContext(),"Erro na consulta",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private class EditaTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Para editar, temos que definir qual código de estado queremos digitar
            String selection = SQLEstadosHelper.STATE_CODE + " = ?";
            //Perceba que neste caso, estamos usando a String de atributo
            // se usarmos o que foi digitado, teremos um problema (como visto na aula)
            String[] selectionArgs = { codigoEstado };

            //Cria objeto com novos valores para UF e Nome do estado
            ContentValues cv = new ContentValues();
            cv.put(SQLEstadosHelper.STATE_CODE,params[0]);
            cv.put(SQLEstadosHelper.STATE_NAME,params[1]);

            //Roda a atualização no banco
            //UPDATE estados SET uf='PE', nome='Pernambuco Imortal' WHERE uf='PE'
            db.getWritableDatabase().update(SQLEstadosHelper.DATABASE_TABLE,cv,selection,selectionArgs);

            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            //Assim que terminar de editar, feche a Activity
            finish();
        }
    }
}
