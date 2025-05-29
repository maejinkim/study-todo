package com.maejin.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maejin.data.TaskDao
import com.maejin.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskLocalDatasource : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}