package ru.mirea.gupalodr.simplefragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var fragment1: Fragment
    lateinit var fragment2: Fragment
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment1 = FirstFragment()
        fragment2 = SecondFragment()
    }

    fun onClick(view: View) {
        fragmentManager = supportFragmentManager
        when (view.id) {
            R.id.btnFirstFragment -> fragmentManager.beginTransaction().replace(
                R.id.fragmentContainer, fragment1
            ).commit()

            R.id.btnSecondFragment -> fragmentManager.beginTransaction().replace(
                R.id.fragmentContainer, fragment2
            ).commit()

            else -> {}
        }
    }
}