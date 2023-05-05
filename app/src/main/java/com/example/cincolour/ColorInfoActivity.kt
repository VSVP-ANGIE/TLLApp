package com.example.cincolour

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ColorInfoActivity : AppCompatActivity() {

    private lateinit var colorTheoryTextView: TextView
    private lateinit var colorPsychologyTextView: TextView
    private lateinit var colorTrendsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_info)

        colorTheoryTextView = findViewById(R.id.colorTheoryTextView)
        colorPsychologyTextView = findViewById(R.id.colorPsychologyTextView)
        colorTrendsTextView = findViewById(R.id.colorTrendsTextView)


        val colorTheoryText = "Understanding the basics of color theory can help you create harmonious and visually pleasing designs. The color wheel is a useful tool for identifying and combining different colors. Some important concepts to keep in mind include:\n\n" +
                "- Complementary Colors: Colors that are opposite each other on the color wheel, such as red and green, can create a strong contrast and add visual interest to your designs.\n\n" +
                "- Analogous Colors: Colors that are next to each other on the color wheel, such as blue and green, can create a calming and cohesive color palette.\n\n" +
                "- Triadic Colors: Colors that are evenly spaced on the color wheel, such as red, blue, and yellow, can create a bold and vibrant color scheme."

        val colorPsychologyText = "Different colors can evoke different emotions and feelings in people. Understanding the psychological effects of color can help you create designs that are more effective in communicating your message. Some examples of color psychology include:\n\n" +
                "- Red: Associated with passion, energy, and excitement. Can also signal danger or warning.\n\n" +
                "- Blue: Associated with calmness, trust, and reliability. Can also be cold or distant.\n\n" +
                "- Yellow: Associated with happiness, optimism, and creativity. Can also be overwhelming or attention-grabbing.\n\n" +
                "- Green: Associated with nature, growth, and harmony. Can also be associated with envy or greed."

        val colorTrendsText = "Keeping up with color trends can help you create designs that feel fresh and modern. Some popular color trends in recent years include:\n\n" +
                "- Bold, saturated colors: Bright, eye-catching colors that demand attention.\n\n" +
                "- Pastel shades: Soft, muted colors that create a calm and soothing atmosphere.\n\n" +
                "- Earth tones: Colors inspired by nature, such as warm browns, greens, and blues."

        colorTheoryTextView.text = colorTheoryText
        colorPsychologyTextView.text = colorPsychologyText
        colorTrendsTextView.text = colorTrendsText
    }
}
