package br.ufpe.cin.android.services

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_thread_service.*

class MainThreadServiceActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_thread_service)

        val serviceIntent = Intent(this, MainThreadService::class.java)

        btn_StartServiceMainThread.setOnClickListener {
            startService(serviceIntent)
            Toast.makeText(applicationContext, "Iniciou o Service", Toast.LENGTH_LONG).show()
        }

        btn_StopServiceMainThread.setOnClickListener {
            stopService(serviceIntent)
        }

        btn_Toast.setOnClickListener { Toast.makeText(applicationContext, "Toast", Toast.LENGTH_SHORT).show() }
    }
}
