package com.example.lab3_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {


    private val requestCode = 1;

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.bnToFirst.setOnClickListener {
            toFirst()
        }

        binding.bnToThird.setOnClickListener {
            toThird()
        }


        binding.navView.setOnNavigationItemReselectedListener { toAbout(it) }
    }


    private fun toFirst() {
        finish()
    }

    private fun toThird() {
        startActivityForResult(Intent(this, ThirdActivity::class.java), requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("STATE", "RESULTCODE is $resultCode")
        if (resultCode == Activity.RESULT_OK) finish()
    }

    private fun toAbout(item: MenuItem) {
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this, ActivityAbout::class.java))
        }
    }

}