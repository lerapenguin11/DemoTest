package com.example.demotest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.demotest.R
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
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_layout)

        if (currentFragment is PaymentsFragment || currentFragment is AuthorizationFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    companion object{
        private const val CODE = 0
        private const val PREF_CODE = "code"
    }
}