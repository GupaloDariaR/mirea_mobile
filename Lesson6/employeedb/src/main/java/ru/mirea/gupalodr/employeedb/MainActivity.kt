package ru.mirea.gupalodr.employeedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db: AppDatabase? = App.instance?.getDatabase()
        val superheroDao: SuperheroDao? = db?.superheroDao()

        var superhero = Superhero()
        superhero.id = 1
        superhero.name = "Мастер разума"
        superhero.skill = "Телекинез"
        superhero.power = 1000

        // запись в базу
        superheroDao?.insert(superhero)

        // Загрузка всех супергероев
        val superheroes: List<Superhero>? = superheroDao?.getAll

        // Получение определенного супергероя с id = 1
        superhero = superheroDao?.getById(1)!!

        // Обновление полей объекта
        superhero.power = 20000
        superheroDao.update(superhero)
        Log.d("superhero", superhero.name + " " + superhero.skill + " " + superhero.power)
    }
}