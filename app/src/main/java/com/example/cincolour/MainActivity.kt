package com.example.cincolour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun colorBlindnessSimulationButton(view: View) {
        val intent = Intent(applicationContext, ColorBlindnessSimulationActivity::class.java)
        startActivity(intent)
    }

    fun colorPaletteButton(view: View) {
        val intent = Intent(applicationContext, ActivityColorPalette::class.java)
        startActivity(intent)
    }

    fun colorInfoButton(view: View) {
        val intent = Intent(applicationContext, ColorInfoActivity::class.java)
        startActivity(intent)
    }

    fun colorBlindTestButton(view: View) {
        val intent = Intent(applicationContext, ColorBlindTestActivity::class.java)
        startActivity(intent)
    }

    fun colorBlindTestButton2(view: View) {
        val intent = Intent(applicationContext, ColorBlindTestActivity2::class.java)
        startActivity(intent)
    }
}