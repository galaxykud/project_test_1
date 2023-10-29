package com.example.testtaskjson.domain

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.testtaskjson.data.Receipt

interface ReceiptRemoteRepository {

    fun getAllReceipt(context: Context) : List<Receipt>


}