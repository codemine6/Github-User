package com.kuswand.githubuser.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kuswand.githubuser.R
import com.kuswand.githubuser.presentation.search_user.SearchUserFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.flContainer, SearchUserFragment())
            commit()
        }
    }
}