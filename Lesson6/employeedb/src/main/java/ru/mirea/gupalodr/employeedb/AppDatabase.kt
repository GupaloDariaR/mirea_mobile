package ru.mirea.gupalodr.employeedb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Superhero::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun superheroDao(): SuperheroDao?
}