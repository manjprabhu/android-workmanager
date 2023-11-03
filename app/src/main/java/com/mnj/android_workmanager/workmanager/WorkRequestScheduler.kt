package com.mnj.android_workmanager.workmanager

import android.content.Context
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit


object WorkRequestScheduler {

    val TASK_KEY = "Input_TASK_KEY"

    var workRetryCount: Int = 0

    fun constructOneTimeWork(context: Context) {
        println("==>> constructOneTimeWork ......")
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val data =
            Data.Builder().putString(TASK_KEY, "This massage send by data !").build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).setInputData(data)
            .setConstraints(constraints).build()
        WorkManager.getInstance(context).enqueue(oneTimeWorkRequest)
    }

    fun oneTimeWorkWithDelay(context: Context) {
        println("==>>  oneTimeWorkWithDelay....")
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()

        val workRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

    // Repeated work
    fun constructRepeatingWork(context: Context) {

        println("==>> constructRepeatingWork")
        // Set Execution around 04:00:00 AM
        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()

        dueDate.set(Calendar.HOUR_OF_DAY, 18)
        dueDate.set(Calendar.MINUTE, 45)
        dueDate.set(Calendar.SECOND, 0)
        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }
        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff)

        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val repeatingWorkRequest = PeriodicWorkRequest.Builder(
            MyWorker::class.java,
            15, TimeUnit.MINUTES
        )
            .setInitialDelay(minutes, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .addTag("myworkmanager")
            .build()

        val periodicWork = PeriodicWorkRequestBuilder<RepetitiveWorker>(3000, TimeUnit.MILLISECONDS)
            .setConstraints(constraints).build()

        WorkManager.getInstance(context).enqueue(periodicWork)

        /* WorkManager.getInstance(context).enqueueUniquePeriodicWork(
             "myworkmanager",
             ExistingPeriodicWorkPolicy.UPDATE, repeatingWorkRequest
         )*/
    }

    fun repetitiveWork(context: Context) {
        println("==>> repetitiveWork .....")
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()

        val workRequest: WorkRequest =
            OneTimeWorkRequestBuilder<MyWorkerRetry>().setConstraints(constraints).build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }


}