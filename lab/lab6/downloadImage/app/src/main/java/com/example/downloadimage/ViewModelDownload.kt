package com.example.downloadimage
import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class ViewModelDownload: ViewModel() {
    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    private val downloadThread : ExecutorService = Executors.newSingleThreadExecutor()

    fun downloadImage(url: URL) {
        downloadThread.execute {
            Thread.sleep(5000)
            Log.d("THREAD", "Downloading in ${Thread.currentThread()}")
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }

}