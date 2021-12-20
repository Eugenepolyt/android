package com.example.continuewatch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.lang.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var isOpen: Boolean = true
    private var isLeft = 0
    var milliFirst: Long = 0
    var milliSecond: Long = 0
    var secondsElapsed: Int = 0
    var onScreen = true
    lateinit var textSecondsElapsed: TextView

    var backgroundThread = Thread {
        while (true) {
            if (onScreen) {
                if (isLeft == 0) milliFirst = System.currentTimeMillis()

                Log.d("STATE", System.currentTimeMillis().toString())
                if (isLeft == 0) { Thread.sleep(1000) }
                else {
                    Log.d("STATE", (milliSecond-milliFirst).toString())
                    isLeft = 0;
                    Thread.sleep(1000 - (milliSecond - milliFirst))

                }
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
        Log.d("STATE", System.currentTimeMillis().toString())
        super.onPause()
        isLeft = 1
        milliSecond = System.currentTimeMillis()
        onScreen = false
        isOpen = false
    }

    override fun onResume() {
        super.onResume()
        onScreen = true
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