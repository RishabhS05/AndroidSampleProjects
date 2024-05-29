package com.example.baseArchitecture.cacheMemory

import android.graphics.Bitmap
import androidx.collection.LruCache

class LRUCacheBitmap {
    private lateinit var memoryCache: LruCache<String, Bitmap>
private fun calculateLRUSize() {
    val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    // Use 1/8th of the available memory for this memory cache.
    val cacheSize = maxMemory / 8

    memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

        override fun sizeOf(key: String, bitmap: Bitmap): Int {
            // The cache size will be measured in kilobytes rather than
            // number of items.
            return bitmap.byteCount / 1024
        }
    }
}
    fun getImageFromCache(key : String ): Bitmap? {
        return memoryCache.get(key)
    }
}