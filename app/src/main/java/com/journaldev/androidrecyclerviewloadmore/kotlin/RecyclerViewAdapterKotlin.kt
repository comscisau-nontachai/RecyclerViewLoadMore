package com.journaldev.androidrecyclerviewloadmore.kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.journaldev.androidrecyclerviewloadmore.R

class RecyclerViewAdapterKotlin(val items: List<String?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.item_row, p0, false)
            return ItemVH(view)
        } else {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.item_loading, p0, false)
            return LoadingVH  (view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
        if (holder is ItemVH) {
            holder.tvText.text = items[p1]
        } else if (holder is LoadingVH) {
            holder.progressBar.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }
}

class ItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvText = itemView.findViewById<TextView>(R.id.tvItem)
}

class LoadingVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
}