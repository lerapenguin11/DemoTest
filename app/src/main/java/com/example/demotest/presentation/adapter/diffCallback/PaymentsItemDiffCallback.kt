package com.example.demotest.presentation.adapter.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.ResponseX

class PaymentsItemDiffCallback : DiffUtil.ItemCallback<ResponseX>() {
    override fun areItemsTheSame(oldItem: ResponseX, newItem: ResponseX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResponseX, newItem: ResponseX): Boolean {
        return oldItem == newItem
    }
}