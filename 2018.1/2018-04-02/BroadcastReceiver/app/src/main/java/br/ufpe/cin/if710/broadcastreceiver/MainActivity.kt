package br.ufpe.cin.if710.broadcastreceiver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enviarBroadcast = findViewById<Button>(R.id.enviarBroadcast)
        enviarBroadcast.setOnClickListener { sendBroadcast(Intent(STA_BROADCAST_ACTION)) }

        val abrirActivity = findViewById<Button>(R.id.abrirActivity)
        abrirActivity.setOnClickListener { startActivity(Intent(applicationContext, DynRecActivity::class.java)) }

        val bateriaActivity = findViewById<Button>(R.id.bateriaActivity)
        bateriaActivity.setOnClickListener { startActivity(Intent(applicationContext, BateriaActivity::class.java)) }
    }

    companion object {
        val STA_BROADCAST_ACTION = "br.ufpe.cin.if710.broadcasts.exemplo"
    }
}
