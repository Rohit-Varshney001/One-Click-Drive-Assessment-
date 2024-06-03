package com.example.oneclickdriveassesment
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Find the theme toggle button
        val themeToggleButton: ImageButton = findViewById(R.id.themeToggleButton)

        // Set a click listener for the theme toggle button
        themeToggleButton.setOnClickListener {
            // Toggle between light and dark themes
            if (isDarkTheme()) {
                // Switch to light theme
                setLightTheme()
            } else {
                // Switch to dark theme
                setDarkTheme()
            }
        }

        // Your existing code continues here
        val textBox1: TextInputEditText = findViewById(R.id.textBox1)
        val textBox2: TextInputEditText = findViewById(R.id.textBox2)
        val textBox3: TextInputEditText = findViewById(R.id.textBox3)
        val calculateButton: MaterialButton = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val list1 = textBox1.text.toString().split(",").map { it.trim().toInt() }
            val list2 = textBox2.text.toString().split(",").map { it.trim().toInt() }
            val list3 = textBox3.text.toString().split(",").map { it.trim().toInt() }

            val intersect = list1.intersect(list2).intersect(list3).joinToString(", ")
            val union = (list1 + list2 + list3).toSet().joinToString(", ")
            val highestNumber = (list1 + list2 + list3).maxOrNull()

            val result = """
                Intersection: $intersect
                Union: $union
                Highest Number: $highestNumber
            """.trimIndent()

            resultTextView.text = result
        }
    }

    // Function to check if the current theme is dark
    private fun isDarkTheme(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    // Function to set the app theme to light theme
    private fun setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        recreate()
    }

    // Function to set the app theme to dark theme
    private fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        recreate()
    }
}
