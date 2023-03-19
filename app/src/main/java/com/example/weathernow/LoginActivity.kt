package com.example.weathernow

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weathernow.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fireBaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fireBaseAuth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val email = binding.editTextLoginEmailAddress.text.toString()
            val pass = binding.editTextLoginPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                fireBaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val verification=fireBaseAuth.currentUser?.isEmailVerified
                        if(verification==true) {
                            val intent = Intent(this, LocationInputActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,"Please Verify Your Email Through Signup Page",Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Either user not signed up or wrong password", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        //Signup page
        btnSignUpPage.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)

            /*Toast.makeText(this,"SignUp Page",Toast.LENGTH_SHORT).show()*/
        }

        //forgot password section
        txtForgotPassword.setOnClickListener {
            val intent =Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}