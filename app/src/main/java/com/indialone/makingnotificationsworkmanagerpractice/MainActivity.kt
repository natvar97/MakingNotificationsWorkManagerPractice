package com.indialone.makingnotificationsworkmanagerpractice

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//
//        findViewById<Button>(R.id.btn_inbox_notification).setOnClickListener {
//
//            val data = Data.Builder().putString("indialone", "this data from MainActivity").build()
//
//            val constraints = Constraints.Builder()
//                .setRequiresBatteryNotLow(true)
//                .build()
//
//            val workRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
//                .setInputData(data)
//                .setConstraints(constraints)
//                .build()
//
//            WorkManager.getInstance(this).enqueue(workRequest)
//
//        }

        startWork()

    }

    private fun createConstraints() = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()


    private fun createWorkRequest() =
        PeriodicWorkRequest.Builder(NotificationWorker::class.java, 2, TimeUnit.SECONDS)
            .setConstraints(createConstraints()).build()

    private fun startWork() {
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "indialone",
            ExistingPeriodicWorkPolicy.KEEP,
            createWorkRequest()
        )
    }


}