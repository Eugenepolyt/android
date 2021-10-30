package com.example.lab3_2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    

    private var launcher: ActivityResultLauncher<Intent>? = null

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) finish()
        }

        binding.toFirst.setOnClickListener {
            toFirst()
        }

        binding.toThird.setOnClickListener {
            toThird()
        }


        binding.toAbout.setOnNavigationItemReselectedListener { toAbout(it) }
    }

    private fun toFirst() {
        finish()
    }

    /*private fun toThird() {
        startActivityForResult(Intent(this, ThirdActivity::class.java), requestCode)
    }*/

    private fun toThird() {
        launcher?.launch(Intent(this, ThirdActivity::class.java))
    }

/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) finish()
    }*/

    private fun toAbout(item: MenuItem) {
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this, ActivityAbout::class.java))
        }
    }

}