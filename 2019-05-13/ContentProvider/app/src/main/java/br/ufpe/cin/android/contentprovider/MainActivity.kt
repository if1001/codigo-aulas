package br.ufpe.cin.android.contentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            startActivity(Intent(getApplicationContext(), LerContatosArrayActivity::class.java))
        }

        btn2.setOnClickListener{
            startActivity(Intent(getApplicationContext(), LerContatosCursorActivity::class.java))
        }

        btn3.setOnClickListener{
            startActivity(Intent(getApplicationContext(), LerContatosLoaderActivity::class.java))
        }

        btn4.setOnClickListener {
            startActivity(Intent(getApplicationContext(), ContentConsumerActivity::class.java))
        }

        btn5.setOnClickListener {
            startActivity(Intent(getApplicationContext(), ContentConsumerSQLiteActivity::class.java))
        }

    }
}
