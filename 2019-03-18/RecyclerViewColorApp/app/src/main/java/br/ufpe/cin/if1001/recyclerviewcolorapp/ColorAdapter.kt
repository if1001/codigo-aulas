package br.ufpe.cin.if1001.recyclerviewcolorapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class ColorAdapter(private val inflater: LayoutInflater) :
    ListAdapter<Int, ColorViewHolder>(ColorDiffer) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorViewHolder {
        return ColorViewHolder(inflater.inflate(R.layout.row, parent, false))
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    private object ColorDiffer : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldColor: Int, newColor: Int): Boolean {
            return oldColor == newColor
        }

        override fun areContentsTheSame(oldColor: Int, newColor: Int): Boolean {
            return areItemsTheSame(oldColor, newColor)
        }
    }
}
