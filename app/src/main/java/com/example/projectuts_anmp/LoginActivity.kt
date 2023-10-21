package com.example.projectuts_anmp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val signInButton: Button = findViewById(R.id.signInButton)
        signInButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username=="" && password=="") {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()  // Finish LoginActivity so the user cannot navigate back to it
            } else {
                // Handle unsuccessful login (show an error message, etc.)
            }
        }
    }
}