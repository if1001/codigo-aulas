package br.ufpe.cin.if1001.recyclerviewcolorapp

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class ColorViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
    private val swatch: View = row.swatch
    private val label: TextView = row.label

    init {
        row.setOnClickListener { _ ->
            Toast.makeText(label.context, label.text, Toast.LENGTH_LONG).show()
        }
    }

    fun bindTo(color: Int) {
        label.text = label.context.getString(R.string.label_template, color)
        swatch.setBackgroundColor(color)
    }
}
