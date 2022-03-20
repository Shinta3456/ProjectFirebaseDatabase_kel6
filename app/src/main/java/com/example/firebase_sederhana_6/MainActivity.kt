package com.example.firebase_sederhana_6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    private lateinit var tambah : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tambah = findViewById(R.id.tambah)
        tambah.setOnClickListener{
            startActivity(Intent(this, Users::class.java))
        }

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        tambah.setOnClickListener {
            savedata()
            val intent = Intent (this, Show::class.java)
            startActivity(intent)
        }
    }
    private fun savedata() {
        val write = textInput.text.toString()

        val userId = ref.push().key.toString()
        val user = Users(userId,write)

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            textInput.setText("")
        }
    }
}