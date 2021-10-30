package com.example.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {

    private val TAG = "States"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d(TAG, "SecondActivity: onCreate()")
        val button: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SecondActivity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SecondActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SecondActivity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SecondActivity: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SecondActivity: onDestroy()")
    }

}