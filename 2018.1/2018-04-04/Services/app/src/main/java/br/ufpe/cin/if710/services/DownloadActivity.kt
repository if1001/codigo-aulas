package br.ufpe.cin.if710.services

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.Button
import android.widget.Toast

class DownloadActivity : Activity() {

    internal var botaoDownload: Button

    private val onDownloadCompleteEvent = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, i: Intent) {
            botaoDownload.isEnabled = true
            Toast.makeText(ctxt, "Download finalizado!", Toast.LENGTH_LONG).show()
            startActivity(Intent(ctxt, DownloadViewActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        botaoDownload = findViewById<View>(R.id.botaoDownload) as Button
        botaoDownload.setOnClickListener {
            botaoDownload.isEnabled = false
            val downloadService = Intent(applicationContext, DownloadService::class.java)
            downloadService.data = Uri.parse(downloadLink)
            startService(downloadService)
        }

        val botaoVisualizar = findViewById<View>(R.id.botaoVisualizar) as Button

        botaoVisualizar.setOnClickListener {
            val viewFile = Intent(applicationContext, DownloadViewActivity::class.java)
            startActivity(viewFile)
        }
    }

    override fun onResume() {
        super.onResume()
        val f = IntentFilter(DownloadService.DOWNLOAD_COMPLETE)
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(onDownloadCompleteEvent, f)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(onDownloadCompleteEvent)
    }

    companion object {

        val downloadLink = "http://www.craftinginterpreters.com/image/representing-code/rows.png"
    }
}
