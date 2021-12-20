package com.example.continuewatch

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {
    var milliFirst: Long = 0;
    var milliSecond: Long = 0;
    var counter = 0;
    var isOpen: Boolean = true
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var preferences: SharedPreferences

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                if (isOpen) secondsElapsed++
                textSecondsElapsed.text = resources.getQuantityString(
                    R.plurals.second_elapsed,
                    secondsElapsed,
                    secondsElapsed
                )

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
        preferences = getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val button: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        isOpen = false
        preferences.edit()
            .putInt("PREF",secondsElapsed)
            .apply()
    }

    override fun onResume() {
        super.onResume()
        isOpen = true
        secondsElapsed = preferences.getInt("PREF", 0)
    }

}