package com.example.demotest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.demotest.R
import com.example.demotest.presentation.adapter.diffCallback.PaymentsItemDiffCallback
import com.example.demotest.presentation.adapter.viewholder.PaymentsViewHolder
import com.example.domain.entity.payment.ResponseX

class PaymentsAdapter : ListAdapter<ResponseX, PaymentsViewHolder>(
    PaymentsItemDiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pay, parent, false)

        return PaymentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentsViewHolder, position: Int) {
        val payment = getItem(position)
        holder.title.text = payment.title
        holder.amount.text = payment.amount.toString()
        holder.create.text = payment.created.toString()
    }
}