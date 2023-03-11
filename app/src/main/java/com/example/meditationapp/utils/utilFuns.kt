package com.example.meditationapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import com.example.meditationapp.screens.APP_ACTIVITY
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

fun bitmapToString(bitmap: Bitmap?): String {
    var resized = bitmap
    if (bitmap?.byteCount!! > 500000) {
        resized = Bitmap.createScaledBitmap(
            bitmap,
            (bitmap.width * 0.2).toInt(),
            (bitmap.height * 0.2).toInt(),
            true
        )
    }
    val baos = ByteArrayOutputStream()
    resized?.compress(Bitmap.CompressFormat.PNG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun stringToBitmap(string: String): Bitmap {
    val imageBytes = Base64.decode(string, 0)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun uriToBitmap(uri: Uri?): Bitmap? {
    return if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images
            .Media.getBitmap(APP_ACTIVITY.contentResolver, uri)
    } else {
        val source = ImageDecoder.createSource(APP_ACTIVITY.contentResolver, uri!!)
        ImageDecoder.decodeBitmap(source)
    }
}

fun calculateCurrentTime(): String {
    val currentDate = Date()
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(currentDate)
}