package com.example.testtaskjson.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.testtaskjson.data.Receipt
import com.example.testtaskjson.databinding.ReceiptItemBinding

class ReceiptsAdapter(private val context: Context) : ListAdapter<Receipt, ReceiptViewHolder>(ReceiptCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val binding = ReceiptItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReceiptViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val receipt = getItem(position)
        with(holder.binding) {
            with(receipt) {
                var requestOptions = RequestOptions()
                requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
                Glide.with(context)
                    .load(image)
                    .apply(requestOptions)
                    .skipMemoryCache(true)//for caching the image url in case phone is offline
                    .into(Image)
                /*cardCount.text = cards?.count().toString()*/
                Protein.text = proteins
                Description.text = description
                Carbos.text = carbos
                Fats.text = fats
                Calories.text = calories
                Headline.text = headline
                Time.text = time
                Name.text = name
                Difficulty.text = difficulty.toString()
            }
        }
    }
}