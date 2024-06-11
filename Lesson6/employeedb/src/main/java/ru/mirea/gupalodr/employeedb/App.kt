package ru.mirea.gupalodr.employeedb

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        var instance: App? = null
    }
    private lateinit var database: AppDatabase
    fun getDatabase(): AppDatabase {
        return database
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }
}