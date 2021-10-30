package com.example.lab3_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab3_4.databinding.ActivityPsychoTestFirstBinding

class PsychoTestFirst : AppCompatActivity() {

    private lateinit var binding: ActivityPsychoTestFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsychoTestFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextQuestion.setOnClickListener { nextQuestion() }
    }

    private fun nextQuestion() {
        val intent = Intent(this, PsychoTestSecond::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
}