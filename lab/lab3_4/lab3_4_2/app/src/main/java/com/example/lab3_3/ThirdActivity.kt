package com.example.lab3_3

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.lab3_3.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnToFirst.setOnClickListener {
            toFirst()
        }

        binding.bnToSecond.setOnClickListener {
            toSecond()
        }

        binding.navView.setOnNavigationItemReselectedListener { toAbout(it) }
    }

    private fun toFirst() {
        val intent = Intent(this, MainActivity::class.java)
            .addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun toSecond() {
        finish()
    }

    private fun toAbout(item: MenuItem) {
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this, ActivityAbout::class.java))
        }
    }
}