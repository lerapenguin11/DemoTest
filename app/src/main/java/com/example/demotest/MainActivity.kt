package com.example.demotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demotest.databinding.ActivityMainBinding
import com.example.demotest.presentation.AuthorizationFragment
import com.example.demotest.utilits.APP_ACTIVITY
import com.example.demotest.utilits.replaceFragmentMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        replaceFragmentMain(AuthorizationFragment())
    }
}