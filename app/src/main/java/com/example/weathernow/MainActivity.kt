package com.example.weathernow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button;
import android.content.Intent;
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up_page.*
import android.view.View;
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSignUp.setOnClickListener {
            val intent=Intent(this,SignUpPage::class.java)
            startActivity(intent)

            /*Toast.makeText(this,"SignUp Page",Toast.LENGTH_SHORT).show()*/
        }
    }
}