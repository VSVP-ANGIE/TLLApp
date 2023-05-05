package com.example.cincolour

import android.app.Activity
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.FileNotFoundException

class ColorBlindnessSimulationActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var selectImageButton: Button
    private lateinit var colorBlindnessSpinner: Spinner

    private var selectedImage: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blindness_simulation)

        imageView = findViewById(R.id.imageView)
        selectImageButton = findViewById<Button>(R.id.selectImageButton)
        colorBlindnessSpinner = findViewById(R.id.colorBlindnessSpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.color_blindness_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            colorBlindnessSpinner.adapter = adapter
        }

        colorBlindnessSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    simulateColorBlindness()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

        selectImageButton.setOnClickListener {
            selectImage()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }

    private fun simulateColorBlindness() {
        val selectedOption = colorBlindnessSpinner.selectedItemPosition
        if (selectedImage != null && selectedOption > 0) {
            val bitmap = BitmapFactory.decodeByteArray(selectedImage, 0, selectedImage!!.size)
            val simulatedBitmap = when (selectedOption) {
                1 -> ColorBlindnessSimulator.simulateProtanopia(bitmap)
                2 -> ColorBlindnessSimulator.simulateDeuteranopia(bitmap)
                3 -> ColorBlindnessSimulator.simulateTritanopia(bitmap)
                else -> bitmap
            }
            Glide.with(this)
                .load(simulatedBitmap)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView)
        } else if (selectedImage != null) {
            val bitmap = BitmapFactory.decodeByteArray(selectedImage, 0, selectedImage!!.size)
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_SELECT_IMAGE) {
            val imageUri = data?.data
            try {
                imageUri?.let {
                    selectedImage =
                        contentResolver.openInputStream(imageUri)?.readBytes()
                    Glide.with(this)
                        .load(selectedImage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(imageView)
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    fun backButton(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val REQUEST_CODE_SELECT_IMAGE = 1
    }

}