package br.ufpe.cin.android.datamanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shared_prefs.*

class SharedPrefsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_prefs)
        play_button.setOnClickListener {

        }
        reset_button.setOnClickListener {

        }
    }

    companion object {
        private val RECORDE = "recorde"
    }
}
