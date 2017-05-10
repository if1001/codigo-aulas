package br.ufpe.cin.if1001.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class AulaContentProvider extends ContentProvider {
    List<Pessoa> pessoas;

    public AulaContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Pessoa p;
        //montar a pessoa
        if (values.containsKey(ContentProviderContract.CPF) && values.containsKey(ContentProviderContract.NOME)) {
            String nome = values.getAsString(ContentProviderContract.NOME);
            //p = new Pessoa();
            //pessoas.add(p);
        }

        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public boolean onCreate() {
        pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(0,"Professor", "1234", "lasdasdmt@cin.ufpe.br"));
        pessoas.add(new Pessoa(1,"Teste 1", "4567", "lmtasd@cin.ufpe.br"));
        pessoas.add(new Pessoa(2,"Nome Longo", "5678", "lmasdasdt@cian.ufpe.br"));
        pessoas.add(new Pessoa(3,"Teste 2", "6789", "lmasdt@cin.ufpe.br"));
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //VISAO GERAL
        //montar um cursor
        //popular com o que quer que eu esteja procurando
        //retornar


        // poderia iniciar com projection
        MatrixCursor cursor = new MatrixCursor(ContentProviderContract.ALL_COLUMNS);

        //br.ufpe.cin.if1001.aulaprovider/pessoa/
        if (isPessoasUri(uri)) {
            //ignorando selection, sortorder, etc.
            for (Pessoa p : pessoas) {
                cursor.addRow(new Object[] {p.get_id(), p.getNome(), p.getCpf(), p.getEmail()});
            }
        }
        //br.ufpe.cin.if1001.aulaprovider/pessoa/123
        else if (isPessoaEspecificaUri(uri)) {
            Integer reqId = Integer.parseInt(uri.getLastPathSegment());
            if (pessoas.get(reqId) !=null ) {
                Pessoa p = pessoas.get(reqId);
                cursor.addRow(new Object[] {p.get_id(), p.getNome(), p.getCpf(), p.getEmail()});
            }
        }
        else {
            throw new UnsupportedOperationException("Not yet implemented");
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // final da URI é composta de digitos?
    private boolean isPessoaEspecificaUri(Uri uri) {
        return uri.getLastPathSegment().matches("\\d+");
    }

    // Final da URI é a tabela de pessoas?
    private boolean isPessoasUri(Uri uri) {
        return uri.getLastPathSegment().equals(ContentProviderContract.PESSOA_TABLE);
    }

}
