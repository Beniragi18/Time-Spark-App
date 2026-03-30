package com.bnrgs.timespark2

import android.view.animation.AlphaAnimation
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link UI components
        val timeText = findViewById<EditText>(R.id.timeText)
        val btnSpark = findViewById<Button>(R.id.btnSpark)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnSuggestions = findViewById<Button>(R.id.btnSuggestions)
        val suggestTxt = findViewById<TextView>(R.id.suggestTxt)
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 500
        suggestTxt.startAnimation(fadeIn)

        // 💬 Suggestions Button (USER INPUT POPUP)
        btnSuggestions.setOnClickListener {

            val inputField = EditText(this)
            inputField.setTextColor(android.graphics.Color.BLACK)

            val dialog = AlertDialog.Builder(this)
                .setTitle("Your Suggestion")
                .setView(inputField)
                .setPositiveButton("Submit") { _, _ ->
                    val userInput = inputField.text.toString()

                    if (userInput.isNotEmpty()) {
                        suggestTxt.text = "💡 $userInput"
                    } else {
                        Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancel", null)
                .create()

            dialog.show()
        }

        // ⚡ Generate Spark
        btnSpark.setOnClickListener {
            btnSpark.setOnClickListener {

                // 🚀 Button click effect
                it.alpha = 0.7f
                it.postDelayed({ it.alpha = 1f }, 150)

                val input = timeText.text.toString().trim().lowercase()

                if (input.isEmpty()) {
                    timeText.error = "Please enter a time"
                    suggestTxt.text = ""
                    return@setOnClickListener
                }

                val spark = when (input) {
                    "morning" -> "☀️ Send a good morning message to someone you care about"
                    "afternoon" -> "🌤️ Share a positive update with a friend"
                    "night" -> "🌙 Reflect on your day or send a kind message"
                    else -> "❓ Try morning, afternoon, or night"
                }

                suggestTxt.text = spark
            }

            val input = timeText.text.toString().trim().lowercase()

            if (input.isEmpty()) {
                timeText.error = "Please enter a time"
                suggestTxt.text = ""
                return@setOnClickListener
            }

            val spark = when (input) {
                "morning" -> "🌅 Send a good morning message to someone you care about"
                "afternoon" -> "☀️ Share a positive update with a friend"
                "night" -> "🌙 Reflect on your day or send a kind message"
                else -> {
                    timeText.error = "Only Morning, Afternoon or Night allowed"
                    ""
                }
            }

            if (spark.isNotEmpty()) {
                timeText.error = null
                suggestTxt.text = spark
            }
        }

        // 🔄 Reset
        btnReset.setOnClickListener {
            timeText.text.clear()
            suggestTxt.text = ""

            Toast.makeText(this, "Ready for your next spark ✨", Toast.LENGTH_SHORT).show()
        }
    }
}