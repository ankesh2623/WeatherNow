package com.example.weathernow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth= FirebaseAuth.getInstance()

        btnReset.setOnClickListener{
            val email=etResetPassEmail.text.toString().trim()
            auth.sendPasswordResetEmail(email).addOnSuccessListener {
                Toast.makeText(this,"Check your Email",Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener{
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
        }

        txtLogin.setOnClickListener{
            finish()
        }

    }
}