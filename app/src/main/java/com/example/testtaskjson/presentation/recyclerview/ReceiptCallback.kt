package com.example.testtaskjson.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.testtaskjson.data.Receipt

object ReceiptCallback: DiffUtil.ItemCallback<Receipt>(){
    override fun areItemsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
        return oldItem == newItem
    }
}