package com.example.lab3_4

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_4.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toFirst.setOnClickListener {
            toFirst()
        }

        binding.toThird.setOnClickListener {
            toThird()
        }


        binding.toAbout.setOnNavigationItemReselectedListener { toAbout(it) }
    }

    private fun toFirst() {
        finish()
    }

    private fun toThird() {
        startActivity(Intent(this, ThirdActivity::class.java))
    }


    private fun toAbout(item: MenuItem) {
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this, ActivityAbout::class.java))
        }
    }

}