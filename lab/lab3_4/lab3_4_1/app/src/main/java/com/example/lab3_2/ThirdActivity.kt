package com.example.lab3_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.lab3_2.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.bnToFirst.setOnClickListener {
            toFirst()
        }

        binding.bnToSecond.setOnClickListener {
            toSecond()
        }

        binding.navView.setOnNavigationItemReselectedListener { toAbout(it) }
    }

    private fun toFirst() {
        this.setResult(RESULT_OK)
        finish()
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