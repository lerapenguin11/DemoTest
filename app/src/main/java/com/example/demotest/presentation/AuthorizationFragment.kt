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
import com.example.demotest.utilits.replaceFragmentMain
import com.example.demotest.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AuthorizationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)

        binding.btAuth.setOnClickListener {
            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()
            editTextChangedListener(binding.etLogin)
            editTextChangedListener(binding.etPassword)
            viewModel.login(login, password)
            isValidToken()
        }

        return binding.root
    }

    private fun isValidToken() {
        viewModel.token.observe(viewLifecycleOwner, Observer {
            if (it?.response?.token != null){
                replaceFragmentMain(PaymentsFragment())
            } else{
                binding.textError.visibility = View.VISIBLE
            }
        })
    }

    private fun editTextChangedListener(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (s.toString().trim { it <= ' ' }. length == 0) {
                    isValidEditText(editText)
                }
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isValidEditText(editText)
            }
            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim { it <= ' ' }. length == 0) {
                    isValidEditText(editText)
                }
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AuthorizationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}