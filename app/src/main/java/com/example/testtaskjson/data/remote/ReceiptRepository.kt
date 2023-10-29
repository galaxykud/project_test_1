package com.example.testtaskjson.data.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.testtaskjson.data.Receipt
import com.example.testtaskjson.domain.ReceiptRemoteRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class ReceiptRepository : ReceiptRemoteRepository{
    private val client = OkHttpClient()

    private val request = Request.Builder()
        .url("https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json")
        .get()
        .build()

    override fun getAllReceipt(context: Context): List<Receipt> {

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException("Запрос к серверу не был успешен:" +
                            " ${response.code} ${response.message}")
                }
                Log.d("repository", "Server: ${response.body}")
                val jsonData: String = response.body?.string() ?: ""
                val jArray = JSONArray(jsonData)
                val receiptList = mutableListOf<Receipt>()
                for(i in 0 until jArray.length()) {
                    val jsonObj = jArray.getJSONObject(i)
                   val receipt = Receipt(
                        calories = jsonObj.getString("calories"),
                        carbos = jsonObj.getString("carbos"),
                        description = jsonObj.getString("description"),
                        difficulty = jsonObj.getString("difficulty").toInt(),
                        fats = jsonObj.getString("fats"),
                        headline = jsonObj.getString("headline"),
                        id = jsonObj.getString("id"),
                        image = jsonObj.getString("image"),
                        name = jsonObj.getString("name"),
                        proteins = jsonObj.getString("proteins"),
                        thumb = jsonObj.getString("thumb"),
                        time = jsonObj.getString("time")
                    )
                    receiptList.add(receipt)
                }
                return receiptList
            }
        } catch (e: IOException) {
            Log.d("repository","Ошибка подключения: $e");
        }

        return emptyList()

    }





}