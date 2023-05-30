package com.soyboy445.pc_jawa_barat.ui.main.tabs

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.soyboy445.pc_jawa_barat.DetailActivity
import com.soyboy445.pc_jawa_barat.R
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import org.json.JSONObject
import java.net.URL


class FoodAdapter(val foods: ArrayList<JSONObject>, val context: Context) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val food: JSONObject = foods.get(p1)
        holder.itemView.itemName.text = food.getString("name")
        holder.itemView.itemPrice.text = food.getString("price")
        holder.itemView.itemName.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("food", food.toString())
            context.startActivity(intent)
        }
    }



    override fun getItemCount() = foods.size
}