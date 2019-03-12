package br.ufpe.cin.if710.datamanagement;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SQLiteActivity extends Activity {

    //Objetos da tela
    ListView listaEstados;
    Button adicionarEstado;

    //Objeto que vai conter a conexão ao banco de dados
    SQLEstadosHelper db;

    //AsyncTask que permite carregar valores do banco
    AsyncTask t = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //Pegando instância do Helper, usando padrão singleton
        db = SQLEstadosHelper.getInstance(getApplicationContext());

        //O que acontece ao clicar no botão Adicionar Estado
        adicionarEstado = findViewById(R.id.btnAdicionaEstado);
        adicionarEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdicionarEstadoActivity.class));
            }
        });

        listaEstados = findViewById(R.id.listaEstados);

        //Definindo um Adapter para a ListView
        // Este adapter envolve dados que vem de uma consulta ao banco
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(
                        //contexto, como estamos acostumados
                        this,
                        //Layout XML de como se parecem os itens da lista
                        R.layout.estado,
                        //Objeto do tipo Cursor, com os dados retornados do banco.
                        //Como ainda não fizemos nenhuma consulta, está nulo.
                        null,
                        //Mapeamento das colunas nos IDs do XML.
                        // Os dois arrays a seguir devem ter o mesmo tamanho
                        new String[] { SQLEstadosHelper.STATE_NAME, SQLEstadosHelper.STATE_CODE },
                        new int[] { R.id.estadoNome, R.id.estadoUF },
                        //Flags para determinar comportamento do adapter, pode deixar 0.
                        0
                );
        //Seta o adapter. Como o Cursor é null, ainda não aparece nada na tela.
        listaEstados.setAdapter(adapter);

        listaEstados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Obtendo um objeto Cursor a partir do Adapter
                Cursor cursor = ((SimpleCursorAdapter) parent.getAdapter()).getCursor();
                //Indo para a posição esperada de acordo com o clique
                cursor.moveToPosition(position);

                //Pegando o valor da UF a partir do Cursor
                String UF = cursor.getString(cursor.getColumnIndex(SQLEstadosHelper.STATE_CODE));

                //Cria Intent explícito passando o código do estado, para uma tela de edição
                /*
                Intent i = new Intent(getApplicationContext(),EditarEstadoActivity.class);
                i.putExtra(SQLEstadosHelper.STATE_CODE, UF);
                startActivity(i);
                /**/

                //Uma alternativa seria rodar o RemoveTask para remover o estado.
                // Se for descomentada a linha abaixo, vai remover o estado quando clicar
                // Aí tem que comentar a parte de cima do startActivity
                t = new RemoveTask().execute(UF);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Assim que inicia a Activity, carregue os dados do banco
        t = new LoadCursorTask().execute();
    }

    @Override
    protected void onDestroy() {
        //Se estiver rolando alguma tarefa Async, cancele
        if (t!=null) {
            t.cancel(true);
        }
        //Fecha o Cursor
        ((SimpleCursorAdapter) listaEstados.getAdapter()).getCursor().close();
        //Fecha conexão com banco de dados
        db.close();
        super.onDestroy();
    }

    //Perceba que temos uma classe ABSTRATA estendendo de AsyncTask.
    // Neste caso, estamos apenas definindo um método mais geral para consultas
    // As classes concretas que estenderem BaseTask vão definir doInBackground
    private abstract class BaseTask<T> extends AsyncTask<T, Void, Cursor> {
        //Método geral para retornar todos os estados ordenados por UF
        Cursor doQuery() {
            Cursor result=
                    db.getReadableDatabase()
                            .query(SQLEstadosHelper.DATABASE_TABLE,
                                    SQLEstadosHelper.columns,
                                    null,
                                    null,
                                    null,
                                    null,
                                    SQLEstadosHelper.STATE_CODE);

            //força a query a ser executada
            //so executa quando fazemos algo que precisa do resultset
            result.getCount();

            return result;
        }

        @Override
        public void onPostExecute(Cursor result) {
            //Troca o Cursor, para exibir os dados na tela
            ((CursorAdapter)listaEstados.getAdapter()).changeCursor(result);
            //Transforma o objeto AsyncTask em nulo
            t = null;
        }
    }

    //AsyncTask para carregar os dados
    private class LoadCursorTask extends BaseTask<Void> {
        @Override
        protected Cursor doInBackground(Void... params) {
            //simplesmente roda a consulta
            return(doQuery());
        }
    }

    //AsyncTask para remover um estado
    private class RemoveTask extends BaseTask<String> {
        @Override
        protected Cursor doInBackground(String... params) {
            //Criando a consulta de remoção
            String where = SQLEstadosHelper.STATE_CODE + " LIKE ?";
            //Pegando o argumento passado, para especificar qual estado será removido
            String[] whereArgs = new String[] { params[0] };
            //Roda uma remoção no banco, algo do tipo DELETE FROM estados WHERE `uf` LIKE 'PE'
            db.getWritableDatabase().delete(SQLEstadosHelper.DATABASE_TABLE, where, whereArgs);
            //Roda a consulta para obter lista atualizada de estados e exibir na tela
            return(doQuery());
        }
    }



}
