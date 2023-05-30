package com.soyboy445.pc_jawa_barat

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitBtn.setOnClickListener {
            if (codeText.text.toString().isEmpty()){
                codeLayout.error = "Tidak boleh kosong!"
            } else {
                GetItem(codeText.text.toString()).execute()
            }
        }
        staffBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    inner class GetItem(val code: String) : AsyncTask<Void, Void, String>(){

        override fun doInBackground(vararg p0: Void?): String? {
            try {
                return sendRequest(
                    "http://10.0.2.2:5000/Api/Table/Code/$code",
                    method = "POST"
                ).body
            } catch (e: Exception){
                e.printStackTrace()
                return """
                    {
                      "id": "a56881f1-ba14-4d4b-ac14-2016c0f6bb1e",
                      "number": 58
                    }
                """.trimIndent()
            }
            }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val response = parseJson(result!!)
            println(response.toString())
            if (response.isEmpty()){
                AlertDialog.Builder(this@MainActivity).setMessage("Kode tidak ditemukan!").show()
            } else {
                val intent = Intent(this@MainActivity, CustomerActivity::class.java)
                intent.putExtra("table", result)
                startActivity(intent)
                finish()
            }
        }

    }
}