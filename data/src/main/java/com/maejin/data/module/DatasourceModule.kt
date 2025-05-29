package com.maejin.data.module

import android.content.Context
import androidx.room.Room
import com.maejin.data.TaskDao
import com.maejin.data.datasource.TaskLocalDatasource
import com.maejin.data.repository.TaskRepositoryImpl
import com.maejin.todo.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskLocalDatasource =
        Room.databaseBuilder(context, TaskLocalDatasource::class.java, "todo.db").build()

    @Provides
    @Singleton
    fun provideTaskDao(datasource: TaskLocalDatasource): TaskDao = datasource.taskDao()

    @Provides
    @Singleton
    fun provideTaskRepository(dao: TaskDao): TaskRepository =
        TaskRepositoryImpl(dao)
}
