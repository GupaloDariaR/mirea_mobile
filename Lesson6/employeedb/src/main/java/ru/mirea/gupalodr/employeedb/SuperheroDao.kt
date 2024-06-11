package ru.mirea.gupalodr.employeedb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface SuperheroDao {
    @get:Query("SELECT * FROM superhero")
    val getAll: List<Superhero>?

    @Query("SELECT * FROM superhero WHERE id = :id")
    fun getById(id: Long): Superhero?

    @Insert
    fun insert(superhero: Superhero?)

    @Update
    fun update(superhero: Superhero?)

    @Delete
    fun delete(superhero: Superhero?)
}
