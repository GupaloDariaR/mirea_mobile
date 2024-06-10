package ru.mirea.gupalodr.mireaproject

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit


class NewWorker(context : Context, params : WorkerParameters): Worker(context, params)  {
    companion object {
        const val TAG = "NewWorker"
    }

    override fun doWork(): Result {
        Log.d(TAG, "doWork: start")
        try {
            TimeUnit.SECONDS.sleep(2)
        } catch (e : InterruptedException){
            e.printStackTrace()
        }

        Log.d(TAG, "doWork: end")
        return Result.success()
    }
}