package com.example.continuewatch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.lang.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
    }

    private fun createThread() = Thread {
        try {
            while (!Thread.currentThread().isInterrupted) {
                Log.d("STATE", "${Thread.currentThread()} is iterating")
                Log.d("STATE", "Time before onPause: ${System.currentTimeMillis()}")
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
        } catch (e: InterruptedException) {
            Log.d("STATE", "${Thread.currentThread()} catch exception")
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

        backgroundThread = createThread()
        backgroundThread.start()
    }

    override fun onStop() {
        super.onStop()
        Log.d("STATE", "onStop")

        backgroundThread.interrupt()
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