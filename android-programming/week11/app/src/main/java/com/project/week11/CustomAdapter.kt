package com.project.week11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val viewModel: MyViewModel) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    inner class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {

    }

    // 뷰 홀더 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_layout, parent, false)
        val viewHolder = ViewHolder(view)

        view.setOnClickListener {
            viewModel.clickItem.value = viewHolder.adapterPosition
        }

        return viewHolder
    }

    // 데이터 채우기
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val text1 = view.findViewById<TextView>(R.id.textView1)
        val text2 = view.findViewById<TextView>(R.id.textView2)

        text1.text = viewModel.items[position].firstName
        text2.text = viewModel.items[position].lastName
    }

    override fun getItemCount(): Int {
        return viewModel.items.size
    }
}