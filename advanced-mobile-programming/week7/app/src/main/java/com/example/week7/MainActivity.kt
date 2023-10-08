package com.example.week7

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

class MyViewModel(context: Context) : ViewModel() {
    private val repository = MyRepository(context)
    val repos = repository.repos

    class Factory(val context: Context): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyViewModel(context) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}


class MyAdapter(val items: List<Repo>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView = v.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_layout, parent, false)
        val viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = items[position].name
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(emptyList())

        myViewModel = ViewModelProvider(this, MyViewModel.Factory(this)).get(MyViewModel::class.java)
        myViewModel.repos.observe(this) { reposD ->

            val repos = reposD.map {
                Repo(it.name, Owner(it.owner), "")
            }
            recyclerView.adapter = MyAdapter(repos)
        }
        startWorker()
    }
    private fun startWorker() {
        //val oneTimeRequest = OneTimeWorkRequest.Builder<MyWorker>()
        //        .build()

        val constraints = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.UNMETERED) // un-metered network such as WiFi
            setRequiresBatteryNotLow(true)
            //setRequiresCharging(true)
            // setRequiresDeviceIdle(true) // android 6.0(M) or higher
        }.build()

        //val repeatingRequest = PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.DAYS)
        val repeatingRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            MyWorker.name,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)


    }

    private fun stopWorker() {
        // to stop the MyWorker
        WorkManager.getInstance(this).cancelUniqueWork(MyWorker.name)
    }

}