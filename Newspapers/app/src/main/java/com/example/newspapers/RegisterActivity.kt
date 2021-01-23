package com.example.newspapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val db = DatabaseHandler(this)
        val editTextmail = findViewById(R.id.mailrtxt) as EditText
        val editTextpass = findViewById(R.id.passrtxt) as EditText
        val logUserBtn = findViewById(R.id.logbtn) as Button
        val deletebtn = findViewById(R.id.deletebtn) as Button
        val upgradebtn = findViewById(R.id.upgradebtn) as Button

        fun mailControl(value: String): Boolean {
            return Regex("[a-zA-ZğĞİıöÖşŞüÜ]{2,}").containsMatchIn(value)
        }

        logUserBtn.setOnClickListener(){
            val user = User(editTextmail.text.toString(), editTextpass.text.toString().toInt())
            db.insertData(user)
            if (editTextmail.text.toString().isEmpty() || editTextpass.text.toString().isEmpty()) {
                Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()

            } else if (!Regex("[a-zA-Z]{2,}@[a-zA-Z]{2,}").containsMatchIn(mailrtxt.text.toString())) {
                Toast.makeText(applicationContext, "E-posta formatı hatalıdır!!", Toast.LENGTH_LONG).show()
            } else if (passrtxt.text.toString().trim().length < 5) {
                Toast.makeText(
                    applicationContext,
                    "Şifre en az 5 karakterden oluşmalıdır!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
        deletebtn.setOnClickListener(){
            db.deleteData()
            deletebtn.performClick()
            Toast.makeText(applicationContext, "En son kayıt silinmiştir..!", Toast.LENGTH_LONG).show()
        }
        upgradebtn.setOnClickListener(){
            val user = User(editTextmail.text.toString(), editTextpass.text.toString().toInt())
            db.updateData(user)
            Toast.makeText(applicationContext, "Kayıt Güncellendi..!", Toast.LENGTH_SHORT).show()
        }
    }
}