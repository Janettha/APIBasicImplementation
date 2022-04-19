package com.janettha.navigationdrawerexample.sys.util.providers

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppExecutors(
    val diskIO: ExecutorService,
    val mainThread: Executor
) {

    constructor() : this(Executors.newSingleThreadExecutor(), MainThreadExecutor())

    class MainThreadExecutor : Executor {

        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

}