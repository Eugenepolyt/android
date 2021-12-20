package com.example.downloadimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.downloadimage.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDownload.setOnClickListener {
            Picasso.get().load(
                "https://yapcdn.net/se4/32/rlgjPOGyJuwTek.png"
            ).into(binding.image);
        }

    }

}
