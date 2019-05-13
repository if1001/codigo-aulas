package br.ufpe.cin.android.contentprovider

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLEstadosHelper
//alternativa
//Context c;

private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

    internal var estadosBrasil: Array<String>

    init {

        estadosBrasil = context.resources.getStringArray(R.array.estadosBrasil)

        //alternativa
        //c = context;
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_CMD)

        val cv = ContentValues()
        cv.put(STATE_NAME, "indefinido")

        for (estado in estadosBrasil) {
            //inserir estado
            cv.put(STATE_CODE, estado)
            db.insert(DATABASE_TABLE, STATE_NAME, cv)
        }

        //alternativa
        //String[] estados = c.getResources().getStringArray(R.array.estadosBrasil);
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        throw RuntimeException("nao se aplica")
    }

    companion object {
        private val DATABASE_NAME = "if710"
        val DATABASE_TABLE = "estados"
        private val DB_VERSION = 1

        private var db: SQLEstadosHelper? = null

        fun getInstance(c: Context): SQLEstadosHelper? {
            if (db == null) {
                db = SQLEstadosHelper(c.applicationContext)
            }
            return db
        }

        val STATE_CODE = "uf"
        val STATE_NAME = "name"
        val _ID = "_id"
        val columns = arrayOf(_ID, STATE_CODE, STATE_NAME)
        private val CREATE_CMD = (
                "CREATE TABLE " + DATABASE_TABLE + " (" + _ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + STATE_CODE + " VARCHAR(2) NOT NULL, "
                        + STATE_NAME + " TEXT NOT NULL)")
    }
}
