package br.ufpe.cin.android.contentprovider

import android.content.ContentResolver
import android.net.Uri

object ContentProviderContract {
    val _ID = "_id"
    val NOME = "nome"
    val CPF = "cpf"
    val EMAIL = "email"
    val MEDIA = "media"
    val PESSOA_TABLE = "pessoa"


    val STATE_CODE = "uf"
    val STATE_NAME = "name"
    val ESTADO_COLUMNS = arrayOf(_ID, STATE_CODE, STATE_NAME)
    val ESTADO_TABLE = "estado"


    val BASE_LIST_URI = Uri.parse("content://br.ufpe.cin.android.listprovider/")
    //URI para tabela
    val CONTENT_LIST_URI = Uri.withAppendedPath(BASE_LIST_URI, PESSOA_TABLE)

    val BASE_SQL_URI = Uri.parse("content://br.ufpe.cin.android.sqlprovider/")
    //URI para tabela
    val CONTENT_ESTADOS_URI = Uri.withAppendedPath(BASE_SQL_URI, ESTADO_TABLE)

    // Mime type para colecao de itens
    val CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/PessoaContentProvider.data.text"

    // Mime type para um item especifico
    val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/PessoaContentProvider.data.text"

    // Todas as colunas do content provider
    val ALL_COLUMNS = arrayOf(_ID, NOME, CPF, EMAIL, MEDIA)
}
