package com.soyboy445.pc_jawa_barat
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

data class Response(val statusCode: Int, val headers: Map<String, List<String>>? = null, val body: String? = null)

fun parseJson(json: String) : ArrayList<JSONObject>{
    try {
        var jsonString: String = json;
        if (!jsonString.contains("[")) {
            jsonString = "[" + jsonString + "]"
        }

        var result: ArrayList<JSONObject> = arrayListOf()
        var jsonArray = JSONArray(jsonString)
        for (jsonIndex in 0..(jsonArray.length() - 1)){
            result.add(jsonArray.getJSONObject((jsonIndex)))
        }
        return result
    } catch  (e: Exception){
        println(e.stackTrace)
        return arrayListOf()
    }

}

fun sendRequest(url: String, method: String = "GET", headers: Map<String, String>? = null, body: String? = null): Response {

    val conn = URL(url).openConnection() as HttpURLConnection
    conn.requestMethod = method;
    conn.doOutput = body != null;
    headers?.forEach{(s1, s2) -> conn.setRequestProperty(s1, s2)}
    Log.d("WOY", conn.requestProperties.toString() );

    if (body != null){
        conn.outputStream.use {
            it.write(body.toByteArray())
        }
    }

    var responseBody = conn.inputStream.use {
        it.readBytes()
    }.toString(Charsets.UTF_8)

    Log.d("WOY", conn.headerFields.toString() );
    Log.d("WOY", responseBody)
    return Response(conn.responseCode, conn.headerFields, responseBody)
}
