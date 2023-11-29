package com.example.demotest.presentation.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demotest.R

class PaymentsViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val title : TextView = view.findViewById(R.id.tv_title)
    val amount : TextView = view.findViewById(R.id.tv_amount)
    val create : TextView = view.findViewById(R.id.tv_create)
}