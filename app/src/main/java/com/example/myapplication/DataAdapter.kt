package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(items: MutableList<Item>, listener: OnItemClickListener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private val items: MutableList<Item>
    private val itemsAll: MutableList<Item>
    private val listener: OnItemClickListener

    init {
        this.items = ArrayList(items)
        this.itemsAll = ArrayList(items)
        this.listener = listener
    }

    fun setItems(itemsNew: List<Item>) {
        items.clear()
        itemsAll.clear()
        items.addAll(itemsNew)
        itemsAll.addAll(itemsNew)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: Item, position: Int)
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.name)
        val ageView: TextView  = itemView.findViewById(R.id.age)
        val breedView: TextView = itemView.findViewById(R.id.breed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = items[position]
        holder.nameView.text = item.name
        holder.ageView.text = item.age.toString()
        holder.breedView.text = item.breed
        holder.itemView.setOnClickListener { listener.onItemClick(item, position) }
    }

    override fun getItemCount() = items.size
}