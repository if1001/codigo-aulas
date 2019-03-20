package br.ufpe.cin.if1001.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lifecycle.*
import org.jetbrains.anko.alert

class LifecycleActivity : AppCompatActivity() {
    private var keyDIGITADO = "oQueFoiDigitado"
    private var keyLIFECYCLE = "lifecycle"
    private var status: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        botaoDialog.setOnClickListener {
            alert("Alguma mensagem...") {
                title = "Titulo"
                positiveButton("Beleza!") {toast("clicou em beleza!")}
            }.show()
        }

        botaoAdicionaTexto.setOnClickListener {
            val oQueFoiDigitado = campoTexto.text
            if (oQueFoiDigitado.isEmpty()) {
                toast("Digite algo, por favor.")
            } else {
                textoDigitado.text = oQueFoiDigitado
            }
        }


        status = "onCreate() de " + this.localClassName
        atualizaLifecycle(status)
        toast(status)
        Log.d(this.localClassName, status)

    }

    override fun onStart() {
        super.onStart()
        status = "onStart() de " + this.localClassName
        atualizaLifecycle(status)
        toast(status)
        Log.d(this.localClassName, status)
    }

    override fun onResume() {
        super.onResume()
        status = "onResume() de " + this.localClassName
        atualizaLifecycle(status)
        toast(status)
        Log.d(this.localClassName, status)
    }

    override fun onRestart() {
        super.onRestart()
        status = "onRestart() de " + this.localClassName
        atualizaLifecycle(status)
        toast(status)
        Log.d(this.localClassName, status)
    }

    override fun onPause() {
        status = "onPause() de " + this.localClassName
        atualizaLifecycle(status)
        toast(status)
        Log.d(this.localClassName, status)
        super.onPause()
    }

    override fun onStop() {
        status = "onStop() de " + this.localClassName
        atualizaLifecycle(status)
        toast(status)
        Log.d(this.localClassName, status)
        super.onStop()
    }

    override fun onDestroy() {
        status = "onDestroy() de " + this.localClassName
        atualizaLifecycle(status)
        toast(status)
        Log.d(this.localClassName, status)
        super.onDestroy()
    }
    private fun atualizaLifecycle(msg: String) {
        val m = lifecycleLog.text.toString()
        lifecycleLog.text = "$msg\n$m"
    }

    fun Any.toast(msg:String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }







    /*

    textoDigitado.text = savedInstanceState?.getString(keyDIGITADO)
    lifecycleLog.text = savedInstanceState?.getString(keyLIFECYCLE)
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(keyDIGITADO,textoDigitado.text.toString())
        outState?.putString(keyLIFECYCLE,lifecycleLog.text.toString())
        super.onSaveInstanceState(outState)
    }
     */

}
