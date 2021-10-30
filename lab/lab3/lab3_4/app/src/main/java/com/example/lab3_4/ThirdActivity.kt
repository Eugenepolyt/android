package com.example.lab3_4

import android.content.Intent
import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.lab3_4.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toFirst.setOnClickListener {
            toFirst()
        }

        binding.toSecond.setOnClickListener {
            toSecond()
        }

        binding.PsychologyTest.setOnClickListener {
            startTest()
        }

        binding.toAbout.setOnNavigationItemReselectedListener { toAbout(it) }
    }

    private fun toFirst() {
        val intent = Intent(this, MainActivity::class.java)
            .addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun toSecond() {
        finish()
    }

    private fun startTest() {
        val intent = Intent(this, PsychoTestFirst::class.java)
            .addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun toAbout(item: MenuItem) {
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this, ActivityAbout::class.java))
        }
    }
}