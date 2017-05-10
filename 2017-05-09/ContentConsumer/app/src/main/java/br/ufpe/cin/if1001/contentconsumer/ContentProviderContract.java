package br.ufpe.cin.if1001.contentconsumer;

import android.content.ContentResolver;
import android.net.Uri;

public final class ContentProviderContract {
    public static final String _ID = "_id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String EMAIL = "email";
    public static final String PESSOA_TABLE = "pessoa";

    private static final Uri BASE_ARRAY_URI = Uri.parse("content://br.ufpe.cin.if1001.arrayprovider/");
    //URI para tabela
    public static final Uri CONTENT_ARRAY_URI = Uri.withAppendedPath(BASE_ARRAY_URI, PESSOA_TABLE);

    private static final Uri BASE_DB_URI = Uri.parse("content://br.ufpe.cin.if1001.dbprovider/");
    //URI para tabela
    public static final Uri CONTENT_DB_URI = Uri.withAppendedPath(BASE_DB_URI, PESSOA_TABLE);

    private static final Uri BASE_AULA_URI = Uri.parse("content://br.ufpe.cin.if1001.aulaprovider/");
    //URI para tabela
    public static final Uri CONTENT_AULA_URI = Uri.withAppendedPath(BASE_AULA_URI, PESSOA_TABLE);

    // Mime type para colecao de itens
    public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/PessoaContentProvider.data.text";

    // Mime type para um item especifico
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/PessoaContentProvider.data.text";

    // Todas as colunas do content provider
    public static final String[] ALL_COLUMNS = { _ID, NOME, CPF, EMAIL };

}
