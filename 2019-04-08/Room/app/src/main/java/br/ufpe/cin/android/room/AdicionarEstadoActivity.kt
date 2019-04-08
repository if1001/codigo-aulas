package br.ufpe.cin.android.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.alterar_estado.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarEstadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alterar_estado)
        btnInserirEstado.setOnClickListener {

        }
    }
}
