package com.example.filmproject.controller

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.filmproject.R
import kotlinx.android.synthetic.main.activity_login_page.*
import org.json.JSONException
import org.json.JSONObject

class LoginPageActivity : AppCompatActivity() {
    var isLogin  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val url = "http://192.168.188.24:3000/login"

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter valid data", Toast.LENGTH_LONG).show()
            } else {
                val queue = Volley.newRequestQueue(this)
                val jsonobj: JSONObject? = null
                jsonobj!!.put("name", username)
                jsonobj!!.put("password", password)

                val req = JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonobj,
                    Response.Listener{ response ->
                        try {
                            Toast.makeText(this , "You have logged in successfully", Toast.LENGTH_LONG).show()
                            isLogin = true
                        }catch (e: JSONException){
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener{
                        try {

                        }catch (e:JSONException){
                            e.printStackTrace()
                        }
                    })

                queue.add(req)
            }
        }
    }

}