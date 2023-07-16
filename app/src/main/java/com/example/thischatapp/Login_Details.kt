package com.example.thischatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login_Details : AppCompatActivity() {

    private  lateinit var edt_email: EditText
    private  lateinit var edt_password: EditText
    private  lateinit var button_login: Button
    private  lateinit var button_Signup: Button

    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_details)

        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()
        edt_email=findViewById(R.id.edt_email)
        edt_password=findViewById(R.id.edi_Password)
        button_login=findViewById(R.id.button_login)
        button_Signup=findViewById(R.id.button_Signup)

        button_Signup.setOnClickListener {
            val intent = Intent(this,Signup_Details::class.java)
            startActivity(intent)
        }

        button_login.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()

            login(email,password);
        }

    }

    private fun login(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //Code for loginin User
                    val intent = Intent(this@Login_Details, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login_Details,"User does not exist", Toast.LENGTH_SHORT).show()
                }
            }

    }
}