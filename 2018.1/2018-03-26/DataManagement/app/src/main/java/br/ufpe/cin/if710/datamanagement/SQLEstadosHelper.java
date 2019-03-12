package br.ufpe.cin.if710.datamanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLEstadosHelper extends SQLiteOpenHelper {
    //Nome do Banco de Dados
    private static final String DATABASE_NAME = "if710";
    //Nome da tabela do Banco a ser usada
    public static final String DATABASE_TABLE = "estados";
    //Versão atual do banco
    private static final int DB_VERSION = 1;

    //Array para popular o banco
    String[] estadosBrasil;

    //alternativa
    //Context c;

    private SQLEstadosHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);

        //Obtendo o array de estados a partir do strings.xml
        estadosBrasil = context.getResources().getStringArray(R.array.estadosBrasil);

        //alternativa
        //c = context;
    }

    private static SQLEstadosHelper db;

    //Definindo Singleton
    public static SQLEstadosHelper getInstance(Context c) {
        if (db==null) {
            db = new SQLEstadosHelper(c.getApplicationContext());
        }
        return db;
    }

    //Definindo constantes que representam os campos do banco de dados
    public final static String STATE_CODE = "uf";
    public final static String STATE_NAME = "name";
    public final static String _ID = "_id";

    //Definindo constante que representa um array com todos os campos
    public final static String[] columns = { _ID, STATE_CODE, STATE_NAME };

    //Definindo constante que representa o comando de criação da tabela no banco de dados
    final private static String CREATE_CMD =
            "CREATE TABLE "+DATABASE_TABLE+" (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + STATE_CODE + " VARCHAR(2) NOT NULL, "
                    + STATE_NAME + " TEXT NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Executa o comando de criação de tabela
        db.execSQL(CREATE_CMD);

        //Cria objeto ContentValues, que facilita manipulação do banco
        ContentValues cv = new ContentValues();
        //Como só temos o código dos estados, deixamos o nome em aberto
        cv.put(STATE_NAME, "indefinido");

        for (String estado : estadosBrasil) {
            //Atualiza o objeto com o código do estado atual
            cv.put(STATE_CODE, estado);
            //Insere no banco de dados
            db.insert(DATABASE_TABLE, STATE_NAME, cv);
        }

        //alternativa
        //String[] estados = c.getResources().getStringArray(R.array.estadosBrasil);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //estamos ignorando esta possibilidade no momento
        throw new RuntimeException("nao se aplica");
    }
}
