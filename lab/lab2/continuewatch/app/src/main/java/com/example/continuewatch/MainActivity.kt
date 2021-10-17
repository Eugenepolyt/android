package com.example.continuewatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var isOpen: Boolean = true
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView

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
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        isOpen = false
    }

    override fun onResume() {
        super.onResume()
        isOpen = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("KEY", secondsElapsed)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt("KEY")
        super.onRestoreInstanceState(savedInstanceState)
    }
}