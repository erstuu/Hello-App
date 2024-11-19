package com.erstuu.uts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.erstuu.uts.model.UserModel
import com.google.android.material.textfield.TextInputEditText

class WelcomeActivity : AppCompatActivity() {

    private lateinit var userName: TextInputEditText
    private lateinit var userEmail: TextInputEditText
    private lateinit var userAge: TextInputEditText
    private lateinit var submitButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        userName = findViewById(R.id.textInputEditTextName)
        userEmail = findViewById(R.id.textInputEditTextEmail)
        userAge = findViewById(R.id.textInputEditTextAge)
        submitButton = findViewById(R.id.submit_button)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupView()
        setupAction()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun setupView() {
        val nameExtra = intent.getStringExtra(NAME)

        val tvName: TextView = findViewById(R.id.tv_name)
        tvName.text = nameExtra
    }

    private fun setupAction() {
        submitButton.setOnClickListener {
            val name = userName.text.toString()
            val email = userEmail.text.toString()
            val ageString = userAge.text.toString()

            if (!checkEligibility(name, ageString, email)) {
                return@setOnClickListener
            }

            val age = ageString.toInt()
            val user = UserModel(name, email, age)

            val intent = Intent(this, DataActivity::class.java)
            intent.putExtra(DATA, user.getUserInfo())
            startActivity(intent)
        }
    }

    private fun checkEligibility(name: String, age: String, email: String): Boolean {
        if (name.isEmpty() || email.isEmpty() || age.isEmpty()) {
            showToast("Name, Email, and Age must be filled")
            return false
        }

        if (age.toInt() < 18) {
            showToast("Sorry, $name, you must be 18 years old or older")
            return false
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            showToast("Invalid email format")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val NAME = "name"
        const val DATA = "data"
    }
}