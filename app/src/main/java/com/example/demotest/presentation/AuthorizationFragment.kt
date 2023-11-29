package com.example.demotest.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.demotest.R
import com.example.demotest.databinding.FragmentAuthorizationBinding
import com.example.demotest.viewmodel.TokenViewModel
import com.example.demotest.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizationFragment : Fragment() {
    private var _binding : FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!
    private val userViewModel by viewModel<UserViewModel>()
    private val tokenViewModel by viewModel<TokenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        clickBtAuth()
        return binding.root
    }

    private fun clickBtAuth() {
        binding.btAuth.setOnClickListener {
            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()
            editTextChangedListener(binding.etLogin)
            editTextChangedListener(binding.etPassword)
            userViewModel.login(login, password)
            isValidToken()
        }
    }

    private fun isValidToken() {
        userViewModel.token.observe(viewLifecycleOwner, Observer {
            if (it?.response?.token != null){
                binding.textError.visibility = View.INVISIBLE
                tokenViewModel.encryptToken(
                    requireContext(),
                    userViewModel.token.value?.response?.token)
                tokenViewModel.getAuth(CODE)
                launchFragment(PaymentsFragment())
            } else{
                binding.textError.visibility = View.VISIBLE
            }
        })
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun editTextChangedListener(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                isValidEditText(editText)
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isValidEditText(editText)
            }
            override fun afterTextChanged(s: Editable) {
                isValidEditText(editText)
            }
        })
    }

    private fun isValidEditText(editText: EditText) {
        val text: String = editText.text.toString()
        if (text.isNotEmpty()) {
            editText.background =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_edit_text)
            binding.textError.visibility = View.INVISIBLE
        } else {
            editText.background =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_edittext_error)
        }
    }

    companion object{
        private const val CODE = 1
    }
}