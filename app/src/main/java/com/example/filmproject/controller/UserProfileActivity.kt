package com.example.filmproject.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmproject.DataSource
import com.example.filmproject.R
import com.example.filmproject.adapters.FilmRecyclerAdapter
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    lateinit var adapter: FilmRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val data = DataSource()
        val adapter = FilmRecyclerAdapter(this, data.films)
        filmsRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        filmsRecyclerView.layoutManager = layoutManager
        filmsRecyclerView.setHasFixedSize(true)

        editProfiletextView.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}