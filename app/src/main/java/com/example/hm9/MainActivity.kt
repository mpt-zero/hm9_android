package com.example.hm9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance();


        val email = findViewById<EditText>(R.id.userEmail)
        val pass = findViewById<EditText>(R.id.userPassword)
        val btn = findViewById<Button>(R.id.submitBtn)
        val rptPass = findViewById<EditText>(R.id.rptPassword)

        btn.setOnClickListener {
            if(!emailChecking(email = email.text.toString())){
                Toast.makeText(this,"Not Valid Email", Toast.LENGTH_LONG).show()
            }else if(passChecking(pass.text.toString())){
                Toast.makeText(this,"Not Valid Password", Toast.LENGTH_LONG).show()
            }else if(pass.text.toString() != rptPass.text.toString()){
                Toast.makeText(this,"Password Doesn't match", Toast.LENGTH_LONG).show()
            }else {
                mAuth.createUserWithEmailAndPassword(email.text.toString(),pass.text.toString())
            }
        }
    }


    fun emailChecking(email: String): Boolean {
        val pattern: Pattern
        val email_check =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(email_check)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun passChecking(s: String?): Boolean {
        val pass_check = Pattern.compile(
                "[a-z0-9\\!\\@\\#\\$]{9,}"
        )
        return !TextUtils.isEmpty(s) && pass_check.matcher(s).matches()
    }
}