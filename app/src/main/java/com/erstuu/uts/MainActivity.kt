package com.erstuu.uts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var nextButton: AppCompatButton
    private lateinit var textInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.next_button)
        textInput = findViewById(R.id.textInputEditText)

        setupAction()
    }

    private fun setupAction() {
        nextButton.setOnClickListener {
            val text = textInput.text.toString()

            if (text.isEmpty()) {
                showToast("Please fill the name")
            } else {
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra(WelcomeActivity.NAME, text)
                startActivity(intent)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}