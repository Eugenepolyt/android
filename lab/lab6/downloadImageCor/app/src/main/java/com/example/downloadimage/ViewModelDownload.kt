package com.example.downloadimage
import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class ViewModelDownload : ViewModel() {

    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun downloadImage(url: URL) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            val pic = BitmapFactory.decodeStream(
                url.openConnection().getInputStream()
            )
            withContext(Dispatchers.Main) {
                bitmap.value = pic
            }
        }

    }

}