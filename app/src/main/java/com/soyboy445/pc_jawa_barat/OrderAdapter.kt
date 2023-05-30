package com.soyboy445.pc_jawa_barat

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.order_item.view.*
import org.json.JSONObject

class OrderAdapter(val orders: ArrayList<JSONObject>) : RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val order = orders.get(position)
        println(order);
        holder.itemView.tableName.text = "Table " + order.getString("number").toString()
        holder.itemView.priceText.text = order.getString("total").toString()
        holder.itemView.code.text = order.getString("code").toString()

    }
    override fun getItemCount() = orders.size
}