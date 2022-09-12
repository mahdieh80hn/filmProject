package com.example.filmproject.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.filmproject.R
import com.example.filmproject.fragments.AddFilmFragment
import com.example.filmproject.fragments.ExploreFragment
import com.example.filmproject.fragments.UserProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val userProfileFragment = UserProfileFragment()
    private val explorePageFragment = ExploreFragment()
    private val addFilmPageFragment = AddFilmFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(userProfileFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.userProfile -> replaceFragment(userProfileFragment)
                R.id.explorePage -> replaceFragment(explorePageFragment)
                R.id.addFilmPage -> replaceFragment(addFilmPageFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}