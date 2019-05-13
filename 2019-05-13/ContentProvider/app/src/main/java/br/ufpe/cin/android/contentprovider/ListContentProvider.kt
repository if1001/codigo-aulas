package br.ufpe.cin.android.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

import java.util.ArrayList

class ListContentProvider : ContentProvider() {
    internal var pessoas: ArrayList<Pessoa> = ArrayList()

    override fun onCreate(): Boolean {
        pessoas.add(Pessoa(0, "Professor", "1234", "lasdasdmt@cin.ufpe.br", 8.50f))
        pessoas.add(Pessoa(1, "Teste 1", "4567", "lmtasd@cin.ufpe.br", 6.50f))
        pessoas.add(Pessoa(2, "Nome Longo", "5678", "lmasdasdt@cin.ufpe.br", 7.50f))
        pessoas.add(Pessoa(3, "Teste 2", "6789", "lmasdt@cin.ufpe.br", 0.50f))
        return true
    }

    // final da URI é composta de digitos?
    private fun isPessoaEspecificaUri(uri: Uri): Boolean {
        //return uri.lastPathSegment!!.matches("\\d+".toRegex())
        return true
    }

    // Final da URI é a tabela de pessoas?
    private fun isPessoasUri(uri: Uri): Boolean {
        return uri.lastPathSegment == ContentProviderContract.PESSOA_TABLE
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        // poderia iniciar com projection
        val cursor = MatrixCursor(ContentProviderContract.ALL_COLUMNS)

        if (isPessoasUri(uri)) {
            //ignorando selection, sortorder, etc.
            for (p in pessoas) {
                cursor.addRow(arrayOf(p._id, p.nome, p.cpf, p.email, p.media))
            }
        } else if (isPessoaEspecificaUri(uri)) {
            val reqId = Integer.parseInt(uri.lastPathSegment)
            if (pessoas[reqId] != null) {
                val p = pessoas[reqId]
                cursor.addRow(arrayOf(p._id, p.nome, p.cpf, p.email))
            }
        } else {
            throw UnsupportedOperationException("Not yet implemented")
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Implement this to handle requests to delete one or more rows.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // TODO: Implement this to handle requests to insert a new row.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        // TODO: Implement this to handle requests to update one or more rows.
        throw UnsupportedOperationException("Not yet implemented")
    }
}
