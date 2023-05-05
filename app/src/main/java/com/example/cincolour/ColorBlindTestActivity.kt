package com.example.cincolour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible

class ColorBlindTestActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var answerRadioGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var diagnosisTextView: TextView
    private lateinit var imageView: ImageView

    private lateinit var proceedButton: Button
    private lateinit var infoTextView: TextView

    private lateinit var backButton: Button


    private val questions = listOf(

        Question(
            "Which number do you see in the circle?",
            R.drawable.color_blindness_test_circles_12,
            listOf("12", "78", "16", "75"),
            0
        ),

        Question(
            "Which number do you see in the circle?",
            R.drawable.color_blindness_test_circles_16,
            listOf("12", "78", "16", "75"),
            2
        ),

        Question(
            "Which number do you see in the circle?",
            R.drawable.color_blindness_test_circles_29,
            listOf("27", "25", "29", "21"),
            2
        ),

        Question(
            "Which number do you see in the circle?",
            R.drawable.color_blindness_test_circles_6,
            listOf("6", "5", "7", "4"),
            0
        ),

        Question(
            "Which number do you see in the circle?",
            R.drawable.color_blindness_test_circles_74,
            listOf("76", "15", "19", "74"),
            3
        ),

        Question(
            "Which number do you see in the circle?",
            R.drawable.color_blindness_test_circles_3,
            listOf("6", "3", "8", "6"),
            1
        )
    )

    private var currentQuestionIndex = 0
    private var diagnosis = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blind_test)

        questionTextView = findViewById(R.id.question_textview)
        answerRadioGroup = findViewById(R.id.answer_radiogroup)
        nextButton = findViewById(R.id.next_question_button)
        diagnosisTextView = findViewById(R.id.result_textview)
        imageView = findViewById(R.id.question_image_view)

        proceedButton = findViewById(R.id.proceed_button)
        infoTextView = findViewById(R.id.testIntro_textView)

        backButton = findViewById(R.id.back_button)

        infoTextView.text = "The Ishihara test is a widely used color perception test that is used to diagnose color blindness.\n" +
                            "This test consists of six colored plates, each containing a pattern of dots of varying colors. \n\n" +
                            "Instructions:\n"+
                            "-Hold your mobile device about 70cm from you\n-Set your device brightness to 100%\n-Wear glasses if you normally do"

        proceedButton.setOnClickListener {
            infoTextView.isVisible = false
            proceedButton.isVisible = false

            questionTextView.isVisible = true
            imageView.isVisible = true
            answerRadioGroup.isVisible = true
            nextButton.isVisible = true
        }

        backButton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        updateQuestion()
        nextButton.setOnClickListener {
            if (answerRadioGroup.checkedRadioButtonId != -1) {
                checkAnswer()
                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    updateQuestion()
                } else {
                    showDiagnosis()
                }
            } else {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.questionText
        imageView.setImageResource(currentQuestion.imageResource)
        answerRadioGroup.clearCheck()
        answerRadioGroup.removeAllViews()

        for ((index, answer) in currentQuestion.answers.withIndex()) {
            val radioButton = RadioButton(this)
            radioButton.id = index
            radioButton.text = answer
            answerRadioGroup.addView(radioButton)
        }
    }


    private fun checkAnswer() {
        val selectedAnswerIndex = answerRadioGroup.checkedRadioButtonId
        val correctAnswerIndex = questions[currentQuestionIndex].correctAnswerIndex
        if (selectedAnswerIndex != correctAnswerIndex) {
            diagnosis += "X"
        }
    }

    private fun showDiagnosis() {

        questionTextView.isVisible = false
        nextButton.isVisible = false
        imageView.isVisible = false
        answerRadioGroup.isVisible = false

        diagnosisTextView.isVisible = true
        backButton.isVisible = true

        when (diagnosis) {
            "" -> diagnosisTextView.text = "You do not appear to be color blind. \n\n" +
                    "To ensure a professional diagnosis of a visual deficiency such as color blindness, it is advisable to seek the services of an optometrist or an ophthalmologist. These healthcare professionals are highly trained and qualified to diagnose and treat a wide range of vision problems, including color blindness."

            "XXX" -> diagnosisTextView.text = "You appear to have Tritanopia (blue-yellow color blindness). \n\n" +
                    "Tritanopia is a type of color blindness that affects a person's ability to distinguish between blue and green hues. People with tritanopia have a reduced sensitivity to blue light, which can make it difficult for them to differentiate between colors that contain blue and green components. \n" +
                    "It is recommended that you consult an optometrist or an ophthalmologist for proper diagnosis and treatment."

            "XX" -> diagnosisTextView.text = "You appear to have Deuteranopia (red-green color blindness). \n\n" +
                    "Deuteranopia is a form of color blindness that affects a person's ability to distinguish between colors in the green-yellow-red part of the spectrum. People with deuteranopia are missing the green-sensitive cones in their eyes, which makes it difficult to distinguish between different shades of green, yellow, and red. \n" +
                    "It is recommended that you consult an optometrist or an ophthalmologist for proper diagnosis and treatment."

            "X" -> diagnosisTextView.text = "You appear to have Protanopia (red-green color blindness). \n\n" +
                    "Protanopia is a type of color blindness that primarily affects the ability to distinguish between red and green colors. People with protanopia have a reduced sensitivity to the color red and see it as more like green.\n" +
                    "It is recommended that you consult an optometrist or an ophthalmologist for proper diagnosis and treatment."

            else -> diagnosisTextView.text = "You appear to have a combination of color blindness types. In this case, it is important to consult with an optometrist or an ophthalmologist for a proper diagnosis and to discuss possible solutions or accommodations. \n\n" +
                    "One possible solution is to use specialized color-correcting lenses or filters that can enhance color perception for some people with color vision deficiencies."
        }
    }

    data class Question(
        val questionText: String,
        val imageResource: Int,
        val answers: List<String>,
        val correctAnswerIndex: Int
    )

}