package com.example.cincolour

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter

object ColorBlindnessSimulator {
    fun simulateProtanopia(bitmap: Bitmap): Bitmap {
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                0.567f, 0.433f, 0.0f, 0.0f, 0.0f,
                0.558f, 0.442f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.242f, 0.758f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f
            )
        )
        return applyColorMatrix(bitmap, colorMatrix)
    }

    fun simulateDeuteranopia(bitmap: Bitmap): Bitmap {
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                0.625f, 0.375f, 0.0f, 0.0f, 0.0f,
                0.7f, 0.3f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.3f, 0.7f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f
            )
        )
        return applyColorMatrix(bitmap, colorMatrix)
    }

    fun simulateTritanopia(bitmap: Bitmap): Bitmap {
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                0.95f, 0.05f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.433f, 0.567f, 0.0f, 0.0f,
                0.0f, 0.475f, 0.525f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f
            )
        )
        return applyColorMatrix(bitmap, colorMatrix)
    }

    private fun applyColorMatrix(bitmap: Bitmap, colorMatrix: ColorMatrix): Bitmap {
        val outputBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(outputBitmap)

        val paint = android.graphics.Paint()
        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        paint.colorFilter = colorFilter

        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        return outputBitmap
    }
}