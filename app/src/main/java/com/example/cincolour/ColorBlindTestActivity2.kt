package com.example.cincolour

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible


class ColorBlindTestActivity2 : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var answerRadioGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var backButton: Button
    private lateinit var questionLayout: LinearLayout
    private lateinit var imageView: ImageView
    private lateinit var scoreTextView: TextView

    private val imgArray = intArrayOf(
        R.drawable.green,
        R.drawable.dark_gray,
        R.drawable.electric_blue,
        R.drawable.beige
    )

    private val questions = listOf(
        "What color is this?",
        "Which of these colors is darker?",
        "Which of these colors is brighter?",
        "What color is this?"
    )

    private val answerChoices = listOf(
        listOf("Red", "Green", "Blue", "Yellow"),
        listOf("Light gray", "Dark gray", "Black", "White"),
        listOf("Navy blue", "Sky blue", "Baby blue", "Electric blue"),
        listOf("Brown", "Pink", "Maroon", "Beige")
    )

    private val correctAnswers = listOf(
        2, // green
        2, // dark gray
        4, // electric blue
        4  // beige
    )

    private var currentQuestion = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blind_test2)

        questionTextView = findViewById(R.id.question_text_view)
        answerRadioGroup = findViewById(R.id.answers_radio_group)
        nextButton = findViewById(R.id.next_question_button)
        backButton = findViewById(R.id.backButton)
        questionLayout = findViewById(R.id.container)
        imageView = findViewById(R.id.image_view)
        scoreTextView = findViewById(R.id.scoreTextView)

        displayQuestion()
        nextButton.setOnClickListener {
            val checkedRadioButton = findViewById<RadioButton>(answerRadioGroup.checkedRadioButtonId)
            if (checkedRadioButton == null) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            } else {
                val answer = answerRadioGroup.indexOfChild(checkedRadioButton) + 1
                checkAnswer(answer)
                answerRadioGroup.clearCheck()
                currentQuestion++
                if (currentQuestion == questions.size) {
                    showScore()
                } else {
                    displayQuestion()
                }
            }
        }

        backButton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayQuestion() {
        questionTextView.text = questions[currentQuestion]
        imageView.setImageResource(imgArray[currentQuestion])
        answerRadioGroup.clearCheck()
        answerRadioGroup.removeAllViews()
        for (i in 0 until answerChoices[currentQuestion].size) {
            val radioButton = RadioButton(this)
            radioButton.text = answerChoices[currentQuestion][i]
            radioButton.id = i + 1
            answerRadioGroup.addView(radioButton)
        }
        answerRadioGroup.check(0)

    }

    private fun checkAnswer(answer: Int) {
        if (answer == correctAnswers[currentQuestion]) {
            score++
        }
    }

    private fun showScore() {

        questionTextView.isVisible = false
        imageView.isVisible = false
        answerRadioGroup.isVisible = false
        scoreTextView.isVisible = true
        nextButton.isVisible = false
        backButton.isVisible = true

        scoreTextView.text = "Your score: $score/${questions.size}"
    }
}
