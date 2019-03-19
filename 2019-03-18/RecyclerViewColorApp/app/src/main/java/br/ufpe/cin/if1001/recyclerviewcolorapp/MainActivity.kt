package br.ufpe.cin.if1001.recyclerviewcolorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                    DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            )
            adapter = ColorAdapter(layoutInflater).apply {
                submitList(buildItems())
            }
        }
    }

    private fun buildItems() = generateSequence { random.nextInt() }
            .take(25)
            .toList()
}
