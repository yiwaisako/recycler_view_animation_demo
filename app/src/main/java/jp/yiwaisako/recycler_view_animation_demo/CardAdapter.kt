package jp.yiwaisako.recycler_view_animation_demo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    val itemCounter = 64

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_empty_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return itemCounter
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}