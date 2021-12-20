package com.example.continuewatch

import android.content.Intent
import android.os.Bundle
import kotlinx.coroutines.*
import android.util.Log
import java.lang.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var backgroundThread: Thread
    private var isLeft = false
    private var milliFirst: Long = 0
    private var milliSecond: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        lifecycleScope.launchWhenResumed {
            while (isActive) {
                Log.d("state", "Coroutine is iterating")
                if (!isLeft) {
                    milliFirst = System.currentTimeMillis()
                    delay(1000)
                } else {
                    Log.d("STATE", "Delay: ${(milliSecond-milliFirst).toString()}")
                    isLeft = false;
                    delay(1000 - (milliSecond - milliFirst))
                }
                textSecondsElapsed.post {
                    secondsElapsed++
                    textSecondsElapsed.text = resources.getQuantityString(
                        R.plurals.second_elapsed,
                        secondsElapsed,
                        secondsElapsed
                    )

                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isLeft = true
        Log.d("STATE", "Time in onPause: ${System.currentTimeMillis()}")
        milliSecond = System.currentTimeMillis()
    }

    override fun onStart() {
        super.onStart()
        Log.d("STATE", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("STATE", "onStop")

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