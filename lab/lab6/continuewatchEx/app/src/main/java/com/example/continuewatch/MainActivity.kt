package com.example.continuewatch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.lang.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    private val pool : ExecutorService = Executors.newSingleThreadExecutor()
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private var isLeft = false
    private var milliFirst: Long = 0
    private var milliSecond: Long = 0

    private lateinit var backgroundFuture: Future<*>

    private fun submitThread(executorService: ExecutorService) = executorService.submit {
        while (true) {
            Log.d("STATE", "${Thread.currentThread()} is iterating")
            if (!isLeft) {
                milliFirst = System.currentTimeMillis()
                Thread.sleep(1000)
            } else {
                Log.d("STATE", "Delay: ${(milliSecond-milliFirst).toString()}")
                isLeft = false;
                Thread.sleep(1000 - (milliSecond - milliFirst))
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

    override fun onPause() {
        super.onPause()
        isLeft = true
        Log.d("STATE", "Time in onPause: ${System.currentTimeMillis()}")
        milliSecond = System.currentTimeMillis()
    }

    override fun onStart() {
        super.onStart()
        backgroundFuture = submitThread(pool)
    }

    override fun onStop() {
        super.onStop()
        backgroundFuture.cancel(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        val button: Button = findViewById(R.id.button)
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