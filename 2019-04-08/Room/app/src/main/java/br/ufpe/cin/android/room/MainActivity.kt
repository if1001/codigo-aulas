package br.ufpe.cin.android.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var estados: Array<Estado>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //O que acontece ao clicar no botão Adicionar Estado
        btnAdicionaEstado.setOnClickListener {
            startActivity(Intent(getApplicationContext(), AdicionarEstadoActivity::class.java))
        }

    }

}
