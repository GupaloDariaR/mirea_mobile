package ru.mirea.gupalodr.employeedb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Superhero {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var name: String = ""
    var skill: String = ""
    var power: Int = 0
}
