package com.project.week11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel by viewModels<MyViewModel>()
        viewModel.addItem(Item("John", "Baker"))
        viewModel.addItem(Item("준영", "허"))
        viewModel.addItem(Item("안드로이드", "13"))


        // CustomAdapter 와 연결
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // FlotingActionButton
        findViewById<FloatingActionButton>(R.id.floatingActionButton)?.setOnClickListener {
            viewModel.addItem(Item("test", "test"))
            adapter.notifyDataSetChanged()
        }

        viewModel.clickItem.observe(this) {
            val item = viewModel.items[it]
            ItemDialog(item).show(supportFragmentManager, "")
        }
    }
}