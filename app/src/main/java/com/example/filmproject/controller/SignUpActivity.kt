package com.example.filmproject.controller

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.filmproject.R
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONException
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    var isSignedUp = false
    var sharedPref : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signupButton.setOnClickListener {
            var username = usernameEditText.text.toString()
            var password = passwordEditText.text.toString()
            var email = emailEditText.text.toString()

            val url = "http://localhost:3000/signup"

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please enter valid data", Toast.LENGTH_LONG).show()
            } else {
                val queue = Volley.newRequestQueue(this)
                val jsonobj = JSONObject()
                jsonobj.put("name", username)
                jsonobj.put("password", password)
                jsonobj.put("email", email)

                val req = JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonobj,
                    Response.Listener{ response ->
                            Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
                            isSignedUp = true
                    },
                    Response.ErrorListener{
                        try {

                        }catch (e:JSONException){
                            e.printStackTrace()
                        }
                    })

                queue.add(req)
                if (isSignedUp){
                    var loginUrl = "http://192.168.188.24:3000/login"
                    var loginJsonObj = JSONObject()
                    loginJsonObj.put("name", username)
                    loginJsonObj.put("password", password)
                    var loginReq = JsonObjectRequest(
                        Request.Method.POST,
                        loginUrl,
                        loginJsonObj,
                        Response.Listener{ response ->
                            try {
                                Toast.makeText(this , "You have logged in successfully", Toast.LENGTH_LONG).show()
                                LoginPageActivity().isLogin = true
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
                    queue.add(loginReq)
                    if(LoginPageActivity().isLogin){
                        sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
                        val sEdit: SharedPreferences.Editor = sharedPref!!.edit()
                        sEdit.putString("name", username)
                        sEdit.putString("password", password)
                        sEdit.putString("email", email)
                        sEdit.apply()

                        intent = Intent(this , MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }


    }

}