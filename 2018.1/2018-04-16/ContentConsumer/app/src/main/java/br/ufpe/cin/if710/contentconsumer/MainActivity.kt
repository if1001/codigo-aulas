package br.ufpe.cin.if710.contentconsumer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : Activity() {

    @Override
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b1 = findViewById(R.id.btn1) as Button
        b1.setOnClickListener(object : View.OnClickListener() {
            @Override
            fun onClick(v: View) {
                startActivity(Intent(getApplicationContext(), LerContatosArrayActivity::class.java))
            }
        })

        val b2 = findViewById(R.id.btn2) as Button
        b2.setOnClickListener(object : View.OnClickListener() {
            @Override
            fun onClick(v: View) {
                startActivity(Intent(getApplicationContext(), LerContatosCursorActivity::class.java))
            }
        })

        val b3 = findViewById(R.id.btn3) as Button
        b3.setOnClickListener(object : View.OnClickListener() {
            @Override
            fun onClick(v: View) {
                startActivity(Intent(getApplicationContext(), LerContatosLoaderActivity::class.java))
            }
        })

        val b4 = findViewById(R.id.btn4) as Button
        b4.setOnClickListener(object : View.OnClickListener() {
            @Override
            fun onClick(v: View) {
                startActivity(Intent(getApplicationContext(), ContentConsumerActivity::class.java))

            }
        })
        val b5 = findViewById(R.id.btn5) as Button
        b5.setOnClickListener(object : View.OnClickListener() {
            @Override
            fun onClick(v: View) {
                startActivity(Intent(getApplicationContext(), ContentConsumerSQLiteActivity::class.java))

            }
        })
    }
}
