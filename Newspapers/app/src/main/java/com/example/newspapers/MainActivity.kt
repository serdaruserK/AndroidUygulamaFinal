package com.example.newspapers

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DatabaseHandler(this)
        val logUserBtn = findViewById(R.id.logbtn) as Button

        val editTextmail = findViewById(R.id.mailrtxt) as EditText
        val editTextpass = findViewById(R.id.passrtxt) as EditText
        val textregister = findViewById(R.id.textregister) as TextView

        fun mailControl(value: String): Boolean {
            return Regex("[a-zA-ZğĞİıöÖşŞüÜ]{2,}").containsMatchIn(value)
        }

        logUserBtn.setOnClickListener {

            val user = User(editTextmail.text.toString(), editTextpass.text.toString().toInt())
            db.insertData(user)
            if (editTextmail.text.toString().isEmpty() || editTextpass.text.toString().isEmpty()) {
                Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()

            } else if (!Regex("[a-zA-Z]{2,}@[a-zA-Z]{2,}").containsMatchIn(mailrtxt.text.toString())) {
                Toast.makeText(applicationContext, "E-posta formatı hatalıdır!!", Toast.LENGTH_LONG).show()
            } else if (passrtxt.text.toString().trim().length < 5) {
                Toast.makeText(applicationContext,"Şifre en az 5 karakterden oluşmalıdır!",Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }

        }

        textregister.setOnClickListener {
            var intent = Intent(this@MainActivity,RegisterActivity::class.java)
            startActivity(intent)

    }
    }
}
