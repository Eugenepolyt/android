package com.example.lab3_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab3_3.databinding.ActivityMainBinding
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toSecond.setOnClickListener {
            toSecond()
        }

        binding.toAbout.setOnNavigationItemReselectedListener { toAbout(it) }
    }

    private fun toSecond() {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun toAbout(item: MenuItem) {
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this, ActivityAbout::class.java))
        }
    }

}