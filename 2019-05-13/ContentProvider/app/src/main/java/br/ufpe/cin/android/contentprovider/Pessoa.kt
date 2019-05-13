package br.ufpe.cin.android.contentprovider

class Pessoa {

    var _id: Int = 0
    var nome: String? = null
    var cpf: String? = null
    var email: String? = null
    var media: Float = 0.toFloat()

    constructor(i: Int, n: String, c: String, e: String) {
        _id = i
        nome = n
        cpf = c
        email = e
        media = 0f
    }

    constructor(i: Int, n: String, c: String, e: String, m: Float) {
        _id = i
        nome = n
        cpf = c
        email = e
        media = m
    }
}