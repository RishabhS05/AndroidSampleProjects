package com.example.baseArchitecture.cacheMemory

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object ImageCacheManager {

    private const val CACHE_DIR_NAME = "image_cache"
    suspend fun saveImageToCache(context: Context, id: String, bitmap: Bitmap) {
        withContext(Dispatchers.IO) {
            val cacheDir = File(context.cacheDir, CACHE_DIR_NAME)
            if (!cacheDir.exists()) {
                cacheDir.mkdirs()
            }

            val imageFile = File(cacheDir, id)
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        }
    }

    suspend fun loadImageFromCache(context: Context, id: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val cacheDir = File(context.cacheDir, CACHE_DIR_NAME)
            val imageFile = File(cacheDir, id)

            if (imageFile.exists()) {
                val inputStream = FileInputStream(imageFile)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                bitmap
            } else {
                null
            }
        }
    }

    suspend fun clearCache(context: Context) {
        withContext(Dispatchers.IO) {
            val cacheDir = File(context.cacheDir, CACHE_DIR_NAME)
            if (cacheDir.exists()) {
                cacheDir.listFiles()?.forEach { it.delete() }
            }
        }
    }
}