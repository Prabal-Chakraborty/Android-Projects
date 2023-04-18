package com.example.scrollviewexample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LogInActivity : AppCompatActivity() {
    lateinit var eMobileNumber: EditText
    lateinit var ePassword: EditText
    lateinit var button: Button
    lateinit var forgetPassword: TextView
    lateinit var register: TextView
    lateinit var sharedPreferences: SharedPreferences
    val validMobileNo = "7063089385"
    val validPassword = arrayOf("tony", "steve", "thor", "hulk", "thanos")
    lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        var isLoggedIn = sharedPreference.getBoolean("isLoggedIn",false)
        if (isLoggedIn){
            val intent = Intent(this@LogInActivity,Avengers::class.java)
            startActivity(intent)
        }else{
            setContentView(R.layout.activity_login)
        }

        title = "Log In"
        eMobileNumber = findViewById(R.id.PhoneNo)
        ePassword = findViewById(R.id.Password)
        button = findViewById(R.id.button)
        forgetPassword = findViewById(R.id.forgotpass)
        register = findViewById(R.id.registet)

        button.setOnClickListener {

            val mobie = eMobileNumber.text.toString()
            val password = ePassword.text.toString()
            var nameOfAvenger = "The Avengers"
            if ((validMobileNo == mobie)) {
                val intent = Intent(this@LogInActivity, Avengers::class.java)
                if (password == validPassword[0]) {

                    nameOfAvenger = "Iron Man"
                    savePreference(nameOfAvenger)
                    intent.putExtra("Name", nameOfAvenger)
                    startActivity(intent)
                } else if (password == validPassword[1]) {

                    nameOfAvenger = "Captain America"
                    savePreference(nameOfAvenger)
                    intent.putExtra("Name", nameOfAvenger)
                    startActivity(intent)
                } else if (password == validPassword[2]) {

                    nameOfAvenger = "Thor"
                    savePreference(nameOfAvenger)
                    intent.putExtra("Name", nameOfAvenger)
                    startActivity(intent)
                } else if (password == validPassword[3]) {

                    nameOfAvenger = "The Hulk"
                    savePreference(nameOfAvenger)
                    intent.putExtra("Name", nameOfAvenger)
                    startActivity(intent)
                } else if (password == validPassword[4]) {

                    nameOfAvenger = "The Avengers"
                    savePreference(nameOfAvenger)
                    intent.putExtra("Name", nameOfAvenger)
                    startActivity(intent)
                }

            } else {
                Toast.makeText(this@LogInActivity, "Incorrect Credentials", Toast.LENGTH_SHORT).show()
            }

        }
        fun onPause() {
            super.onPause()
            finish()
        }
    }
    //Make sure the fun is outside the onCreate() method but inside the LogInActivity Class
    fun savePreference(title: String){
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
        sharedPreferences.edit().putString("Title",title).apply()
    }
}

