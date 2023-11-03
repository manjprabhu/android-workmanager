package com.mnj.android_workmanager.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RepetitiveWorker(appContext: Context, workParams: WorkerParameters) :
    Worker(appContext, workParams) {

    override fun doWork(): Result {
        println("==>> Repeating work in progress....")
        return Result.success()
    }
}