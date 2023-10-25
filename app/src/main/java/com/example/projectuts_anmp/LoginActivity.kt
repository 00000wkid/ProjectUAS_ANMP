package com.example.projectuts_anmp
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val signInButton: Button = findViewById(R.id.signInButton)

        val sessionManager = SessionManager(this)

        val token=sessionManager.getToken()
        if(token=="ada"){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        signInButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val usernamee=sessionManager.getUsername()
            val passwordd=sessionManager.getPassword()

            if (username==usernamee && password==passwordd ) {
                sessionManager.savePassword(password)
                sessionManager.saveUsername(username)
                sessionManager.saveToken("ada")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                usernameEditText.error = "Username atau password salah"
                passwordEditText.error = "Username atau password salah"
            }
        }
    }
}