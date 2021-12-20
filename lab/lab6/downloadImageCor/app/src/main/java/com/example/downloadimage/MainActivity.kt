package com.example.downloadimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.downloadimage.databinding.ActivityMainBinding
import androidx.activity.viewModels
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainViewModel: ViewModelDownload by viewModels()
        binding.btnDownload.setOnClickListener {
            mainViewModel.downloadImage(URL("https://yapcdn.net/se4/32/rlgjPOGyJuwTek.png"))
        }
        mainViewModel.bitmap.observe(this) {
            binding.image.setImageBitmap(it)
        }

    }

}
