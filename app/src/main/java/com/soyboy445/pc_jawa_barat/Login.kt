package com.soyboy445.pc_jawa_barat

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        customerBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginStaffBtn.setOnClickListener {
            var validate = false
            if (emailText.text.toString().isEmpty()){
                emailLayout.error = "Required"
            }  else {
                emailLayout.error = null
                validate = true
            }
            if (passwordText.text.toString().isEmpty()) {
                passwordLayout.error = "Required"
                validate = false
            } else {
                passwordLayout.error = null
                validate = true
            }
            if (validate){
                GetItem(emailText.text.toString(), passwordText.text.toString()).execute()
            }
        }

    }
    inner class GetItem(val email: String, val password: String) : AsyncTask<Void, Void, String>(){

        override fun doInBackground(vararg p0: Void?): String? {
            try {
                return sendRequest(
                    "http://10.0.2.2:5000/Api/Auth",
                    method = "POST",
                    body = """
                            {
                                email: $email,
                                password: $password,
                            }
                        """.trimIndent()
                ).body
            } catch (e: Exception){
                e.printStackTrace()
                return """
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9lbWFpbGFkZHJlc3MiOiJpY2xhcnJpY29hdGVzM0BjbGlja2JhbmsubmV0IiwiTXlFbWFpbCI6ImljbGFycmljb2F0ZXMzQGNsaWNrYmFuay5uZXQiLCJqdGkiOiIwZmJkOGZlMy01MjIyLTQ1YjMtYWZlZS05MWQ3ZDUzOGUwZTkiLCJleHAiOjE2NjY3NTkyMjksImlzcyI6Imh0dHBzOi8vc21rLnB1c2F0cHJlc3Rhc2luYXNpb25hbC5rZW1kaWtidWQuZ28uaWQvIiwiYXVkIjoiaHR0cHM6Ly9zbWsucHVzYXRwcmVzdGFzaW5hc2lvbmFsLmtlbWRpa2J1ZC5nby5pZC8ifQ.oKLp3rzEk8iulCHwUouWHcQjpBWIXknx__zlk0PvPKc",
  "expired": "2022-10-26T11:40:29.8753485+07:00"
}
                """.trimIndent()
            }
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val response = parseJson(result!!)
            println(response.toString())
            if (response.isEmpty()){
                AlertDialog.Builder(this@Login).setMessage("Akun tidak ditemukan!").show()
            } else {
                val intent = Intent(this@Login, ListTableActivity::class.java)
                intent.putExtra("token", result)
                startActivity(intent)
                finish()
            }
        }

    }


}