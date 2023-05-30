package com.soyboy445.pc_jawa_barat.ui.main.tabs

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import com.soyboy445.pc_jawa_barat.CustomerActivity
import com.soyboy445.pc_jawa_barat.parseJson
import com.soyboy445.pc_jawa_barat.sendRequest
import com.soyboy445.pc_jawa_barat.R

class Menu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val categoryList = view.findViewById<TextInputEditText>(R.id.categoryText)
        val itemList = view.findViewById<RecyclerView>(R.id.itemList)

        itemList.layoutManager = GridLayoutManager(context, 2)
        GetItem(itemList, categoryList).execute()


        return view
    }

    inner class GetItem(val itemList: RecyclerView, val category: TextInputEditText) : AsyncTask<Void, Void, String>(){

        override fun doInBackground(vararg p0: Void?): String? {
            try{
                return sendRequest(
                    "http://10.0.2.2:5000/Api/Menu/Category/${category.text}"
                ).body
            } catch (e: Exception){
                return """
                    [
                      {
                        "id": "8aab6c62-8212-45f6-9ebe-8e5fc433c31d",
                        "name": "Ayam Krispy McDonald's",
                        "price": 89000
                      },
                      {
                        "id": "69ec0d0e-8d69-4be6-bfc5-ac37d7d3f65d",
                        "name": "Ayam Spicy McDonald's",
                        "price": 39000
                      },
                      {
                        "id": "8b709dc9-adf0-4f63-9f5e-c185936e74c1",
                        "name": "Chicken Burger",
                        "price": 52000
                      },
                      {
                        "id": "910f823f-c418-405c-97b6-a42b4ff53821",
                        "name": "Chicken Burger Deluxe",
                        "price": 69000
                      },
                      {
                        "id": "a22fe252-54a9-41f6-891e-159ee9254028",
                        "name": "Chicken Fingers",
                        "price": 33000
                      },
                      {
                        "id": "e8ab287d-c99a-4b02-9d87-5463181b3250",
                        "name": "Chicken Snack Wrap",
                        "price": 31500
                      },
                      {
                        "id": "407e3a14-550d-400d-92dd-423fc2649eef",
                        "name": "Honey Garlic Chicken Rice",
                        "price": 75000
                      },
                      {
                        "id": "f35f154e-12ab-4ce8-96cb-fb51d4039ece",
                        "name": "McChicken",
                        "price": 94000
                      },
                      {
                        "id": "a992565b-25de-4edb-9d00-345613990e11",
                        "name": "McNuggets",
                        "price": 20500
                      },
                      {
                        "id": "c1f69adb-0184-4ca8-b2c1-074727e8300e",
                        "name": "McSpicy",
                        "price": 11500
                      },
                      {
                        "id": "1357b022-5c52-45d5-b466-e39e2e7374f2",
                        "name": "PaMer 5",
                        "price": 46000
                      },
                      {
                        "id": "9182450a-79d7-4212-ada0-02d034968ce7",
                        "name": "PaMer 7",
                        "price": 35500
                      },
                      {
                        "id": "0f4c5c55-5303-4545-b137-f5737de5a47d",
                        "name": "PaNas 1",
                        "price": 93500
                      },
                      {
                        "id": "fb9be4a3-3d5d-41d0-81af-6397a61f1d16",
                        "name": "PaNas 2 with Fries",
                        "price": 82000
                      },
                      {
                        "id": "8ca9b564-5301-487c-80b9-c5ef07fcce10",
                        "name": "PaNas 2 with Rice",
                        "price": 62500
                      },
                      {
                        "id": "284c40cc-bbe9-4553-984b-0510cbe71e6c",
                        "name": "PaNas Spesial",
                        "price": 74500
                      },
                      {
                        "id": "3fb875d4-e813-4ef4-b84b-c80fcf88e4fe",
                        "name": "Rica-rica Chicken Rice",
                        "price": 35500
                      },
                      {
                        "id": "48da7e55-d862-452f-82a1-6f9e975ef61f",
                        "name": "Spicy Chicken Bites",
                        "price": 97500
                      }
                    ]
                """.trimIndent()
            }

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val foods = parseJson(result!!)
            itemList.adapter = FoodAdapter(foods, context!!)
        }



    }

}