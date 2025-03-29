package com.example.lab1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var scrambledWordText: TextView
    private lateinit var userGuessEditText: EditText
    private lateinit var messageText: TextView
    private lateinit var submitButton: Button

    private val wordList = listOf("android", "kotlin", "compose", "studio", "developer")
    private var currentWord = wordList.random()
    private var scrambledWord = scrambleWord(currentWord)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        scrambledWordText = findViewById(R.id.scrambled_word)
        userGuessEditText = findViewById(R.id.user_guess)
        messageText = findViewById(R.id.message)
        submitButton = findViewById(R.id.submit_button)

        // Display scrambled word
        scrambledWordText.text = "Scrambled Word: $scrambledWord"

        // Set up the submit button click listener
        submitButton.setOnClickListener {
            val userGuess = userGuessEditText.text.toString()

            if (userGuess.equals(currentWord, ignoreCase = true)) {
                messageText.text = "Correct! Starting new game..."
                currentWord = wordList.random()
                scrambledWord = scrambleWord(currentWord)
                scrambledWordText.text = "Scrambled Word: $scrambledWord"
                userGuessEditText.text.clear()
            } else {
                messageText.text = "Wrong! Try Again."
            }
        }
    }

    // Function to scramble the word
    private fun scrambleWord(word: String): String {
        val charArray = word.toCharArray()
        charArray.shuffle(Random(System.nanoTime())) // Scrambling with a random seed
        return String(charArray)
    }
}