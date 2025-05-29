package com.maejin.todo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize any global resources or libraries here
        // For example, you can initialize logging, crash reporting, etc.
    }
}