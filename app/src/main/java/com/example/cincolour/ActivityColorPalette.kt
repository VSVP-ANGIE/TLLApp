package com.example.cincolour

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class ActivityColorPalette : AppCompatActivity() {

    private lateinit var colorPaletteContainer: LinearLayout
    private lateinit var btnGeneratePalette: Button

    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_palette)

        colorPaletteContainer = findViewById(R.id.color_palette_container)
        btnGeneratePalette = findViewById(R.id.btn_generate_palette)

        btnBack = findViewById(R.id.buttonBack2)

        btnBack.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnGeneratePalette.setOnClickListener {
            generateColorPalette()
            btnBack.isVisible = true
        }
    }

    private fun generateColorPalette() {
        // Remove any existing color bars
        colorPaletteContainer.removeAllViews()

        // Generate 5 random colors and create a color bar for each one
        for (i in 1..5) {
            val color = generateRandomColor()
            val textColor = getInverseTextColor(color)
            val hexCode = colorToHex(color)

            val colorBar = createColorBar(color, textColor, hexCode)
            colorPaletteContainer.addView(colorBar)

            // Add spacing between the color bars
            if (i < 5) {
                val spacingView = View(this)
                spacingView.layoutParams = LinearLayout.LayoutParams(
                    resources.getDimensionPixelSize(R.dimen.color_bar_spacing),
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                colorPaletteContainer.addView(spacingView)
            }
        }
    }

    private fun generateRandomColor(): Int {
        // Generate a random color by choosing random RGB values
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        return Color.rgb(r, g, b)
    }

    private fun getInverseTextColor(color: Int): Int {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.rgb(255 - red, 255 - green, 255 - blue)
    }

    private fun colorToHex(color: Int): String {
        // Convert the color to a hex code string
        return String.format("#%06X", 0xFFFFFF and color)
    }

    private fun createColorBar(color: Int, textColor: Int, hexCode: String): LinearLayout {
        // Create a new LinearLayout to hold the color bar and hex code
        val colorBar = LinearLayout(this)
        colorBar.orientation = LinearLayout.VERTICAL
        colorBar.layoutParams = LinearLayout.LayoutParams(
            resources.getDimensionPixelSize(R.dimen.color_bar_width),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Create a new View for the color bar
        val colorView = View(this)
        colorView.setBackgroundColor(color)
        colorView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            resources.getDimensionPixelSize(R.dimen.color_bar_height)
        )
        colorBar.addView(colorView)

        // Create a new TextView for the hex code
        val hexCodeView = TextView(this)
        hexCodeView.text = hexCode
        hexCodeView.setTextColor(textColor)
        hexCodeView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        hexCodeView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        colorBar.addView(hexCodeView)

        return colorBar
    }
}