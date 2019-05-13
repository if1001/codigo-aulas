package br.ufpe.cin.android.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

import java.util.ArrayList

class SQLiteContentProvider : ContentProvider() {

    internal var db: SQLEstadosHelper? = null

    override fun onCreate(): Boolean {
        db = SQLEstadosHelper.getInstance(context)
        return true
    }

    // Final da URI é a tabela de estados?
    private fun isEstadosUri(uri: Uri): Boolean {
        return uri.lastPathSegment == ContentProviderContract.ESTADO_TABLE
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        //VISAO GERAL
        //montar um cursor
        //popular com o que quer que eu esteja procurando
        //retornar

        var cursor: Cursor? = null

        if (isEstadosUri(uri)) {
            cursor = db?.readableDatabase?.query(SQLEstadosHelper.DATABASE_TABLE, projection, selection, selectionArgs, null, null, sortOrder)
        } else {
            throw UnsupportedOperationException("Not yet implemented")
        }
        return cursor
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Implement this to handle requests to delete one or more rows.
        var i = 0
        if (isEstadosUri(uri)) {
            val x = db?.writableDatabase?.delete(SQLEstadosHelper.DATABASE_TABLE, selection, selectionArgs)
            if (x!=null) {
                i = x
            }
        }
        return i

    }

    override fun getType(uri: Uri): String? {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (isEstadosUri(uri)) {
            val id = db?.writableDatabase?.insert(SQLEstadosHelper.DATABASE_TABLE, null, values)
            if (id!=null) {
                return Uri.withAppendedPath(ContentProviderContract.CONTENT_ESTADOS_URI, java.lang.Long.toString(id))
            }
        }
        return null
    }


    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        var i = 0
        if (isEstadosUri(uri)) {
            val x = db?.writableDatabase?.update(SQLEstadosHelper.DATABASE_TABLE, values, selection, selectionArgs)
            if (x!=null) {
                i = x
            }
        }
        return i
    }
}
