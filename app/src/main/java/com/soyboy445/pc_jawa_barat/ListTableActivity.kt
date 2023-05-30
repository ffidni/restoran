package com.soyboy445.pc_jawa_barat

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.soyboy445.pc_jawa_barat.ui.main.tabs.FoodAdapter
import kotlinx.android.synthetic.main.activity_list_table.*

class ListTableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_table)
        tableList.layoutManager = LinearLayoutManager(this)

        GetItem(tableList).execute()
    }
    inner class GetItem(val itemList: RecyclerView) : AsyncTask<Void, Void, String>(){

        override fun doInBackground(vararg p0: Void?): String? {
            try{
                return sendRequest(
                    "http://localhost:5000/Api/Table",
                ).body
            } catch (e: Exception){
                return """
[
  {
    "id": "2058ac65-e46f-4a18-88bd-8302a7e43857",
    "number": 0,
    "code": "lLPQv1",
    "total": 0
  },
  {
    "id": "b5264ad0-5c32-424d-8e20-8ae1bfcac499",
    "number": 1,
    "code": "8tY6mH",
    "total": 909500
  },
  {
    "id": "bf10a338-02e2-4ebe-8fb6-ea35463a49ca",
    "number": 2,
    "code": "38h524",
    "total": 807000
  },
  {
    "id": "9b2f89c6-f23b-490c-9414-f2255d086003",
    "number": 3,
    "code": "679ft0",
    "total": 1178000
  },
  {
    "id": "97aa34e8-d2d7-43c6-9645-b5762370ea77",
    "number": 4,
    "code": "o1n29Y",
    "total": 1245500
  },
  {
    "id": "f7329747-3d44-4aef-a5e6-0724476c2b1a",
    "number": 6,
    "code": "4uJ8O2",
    "total": 492500
  },
  {
    "id": "39ed71ee-dde1-48a2-8533-9d8db5c49ee0",
    "number": 7,
    "code": "0acg2Y",
    "total": 1025000
  },
  {
    "id": "fbc921af-6c1b-4bb9-ab4d-f9baffacee7a",
    "number": 8,
    "code": "nax5q4",
    "total": 1012500
  },
  {
    "id": "6ff6b558-2091-4315-9b00-48ded46a58af",
    "number": 16,
    "code": "4M3fe1",
    "total": 2336500
  },
  {
    "id": "d1f1c47b-99b9-49f5-b5b8-041ca0661a7e",
    "number": 17,
    "code": "e3711Z",
    "total": 1427000
  },
  {
    "id": "9ab62393-9e57-451d-b325-1f4aa3d8bcff",
    "number": 20,
    "code": "Zdb42m",
    "total": 747000
  },
  {
    "id": "a56881f1-ba14-4d4b-ac14-2016c0f6bb1e",
    "number": 58,
    "code": "5lvvfh",
    "total": 0
  }
]
                """.trimIndent()
            }

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val orders = parseJson(result!!)
            itemList.adapter = OrderAdapter(orders)
        }



    }
}