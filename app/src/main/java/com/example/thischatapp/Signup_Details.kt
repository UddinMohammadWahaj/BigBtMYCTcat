package com.example.thischatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup_Details : AppCompatActivity() {
    private  lateinit var edt_name: EditText
    private  lateinit var edt_email: EditText
    private  lateinit var edt_password: EditText

    private  lateinit var button_Signup: Button

    private lateinit var mAuth: FirebaseAuth

    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_details)

        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()
        edt_name=findViewById(R.id.edt_name)
        edt_email=findViewById(R.id.edt_email)
        edt_password=findViewById(R.id.edi_Password)
        button_Signup=findViewById(R.id.button_Signup)

        button_Signup.setOnClickListener {
            val name = edt_name.text.toString()
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()

            signup(name, email,password)
        }

    }
    private fun signup(name:String, email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    //code for jumping to home
                    val intent = Intent(this@Signup_Details, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Signup_Details,"some error has occured", Toast.LENGTH_SHORT).show()
                    // If sign in fails, display a message to the user.

                }
            }

    }
    private fun addUserToDatabase(name: String,email:String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))


    }
}