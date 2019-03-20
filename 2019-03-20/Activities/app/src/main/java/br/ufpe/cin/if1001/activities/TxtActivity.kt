package br.ufpe.cin.if1001.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_txt.*;

class TxtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_txt)

        //val i = intent
        assunto.text = intent.getStringExtra(Intent.EXTRA_SUBJECT)
        texto.text = intent.getStringExtra(Intent.EXTRA_TEXT)
    }
}
