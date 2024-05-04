package com.example.baseArchitecture.bitmapDownloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class BitmapDownloader {
    suspend fun getImageBitmap(url: String ): Bitmap?{
        var bitmap = withContext(Dispatchers.IO) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream = BufferedInputStream(connection.inputStream)
            val bmp = BitmapFactory.decodeStream(inputStream)
            connection.disconnect()
            bmp
        }
        return bitmap
    }
}