package br.ufpe.cin.if710.adapters

data class Pessoa (val nome: String, val login: String) {
    val email: String
    val site: String
    init {
        this.email = "$login@cin.ufpe.br"
        this.site = "http://www.cin.ufpe.br/~$login"
    }

    override fun toString(): String {
        return "Prof. $nome"
        //return "$nome  ( $login )"
    }
}