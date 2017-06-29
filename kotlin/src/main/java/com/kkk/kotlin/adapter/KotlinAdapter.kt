package com.kkk.kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kkk.kotlin.R

/**
 * Created by dayima on 17-6-7.
 */
class KotlinAdapter (val items:List<String>) : RecyclerView.Adapter<KotlinAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textView?.text = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_kotlin,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val textView = view.findViewById(R.id.tv_context) as TextView
    }
}