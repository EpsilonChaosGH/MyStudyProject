package com.example.mystudyproject.player

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TestPlayerWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    private val handler = Handler(Looper.getMainLooper())

    var currentTime: Date = Calendar.getInstance().time
    val time = SimpleDateFormat("EEE, d MMMM HH:mm", Locale.getDefault()).format(currentTime)

    override fun doWork(): Result {
        println("WORK $time")
        handler.post {

            Toast.makeText(context, "WORKER", Toast.LENGTH_SHORT).show()
        }
        return Result.success()
    }
}