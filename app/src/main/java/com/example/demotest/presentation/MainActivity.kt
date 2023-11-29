package com.example.demotest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demotest.databinding.ActivityMainBinding
import com.example.demotest.presentation.AuthorizationFragment
import com.example.demotest.presentation.PaymentsFragment
import com.example.demotest.utilits.APP_ACTIVITY
import com.example.demotest.utilits.replaceFragmentMain
import com.example.demotest.viewmodel.TokenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<TokenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        val code = viewModel.codeSher.getInt(PREF_CODE, CODE)
        if (code == 1){
            replaceFragmentMain(PaymentsFragment())
        } else{
            replaceFragmentMain(AuthorizationFragment())
        }
    }

    companion object{
        private const val CODE = 0
        private const val PREF_CODE = "code"
    }
}