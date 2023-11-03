package com.mnj.android_workmanager.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorkerRetry(appContext: Context, workParams: WorkerParameters) :
    Worker(appContext, workParams) {

    var workRetryCount: Int = 0
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }


}