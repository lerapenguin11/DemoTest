package com.example.demotest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.demotest.R
import com.example.demotest.databinding.FragmentPaymentsBinding
import com.example.demotest.presentation.adapter.PaymentsAdapter
import com.example.demotest.viewmodel.TokenViewModel
import com.example.demotest.viewmodel.PaymentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentsFragment : Fragment() {
    private var _binding : FragmentPaymentsBinding? = null
    private val binding get() = _binding!!
    private val paymentsViewModel by viewModel<PaymentsViewModel>()
    private val tokenViewModel by viewModel<TokenViewModel>()
    private lateinit var adapter : PaymentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenViewModel.decryptToken(requireContext())?.let { paymentsViewModel.getPayments(it) }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        setRecyclerViewPayment()
        clickBtLogout()
        return binding.root
    }

    private fun clickBtLogout() {
        binding.icExit.setOnClickListener {
            tokenViewModel.codeSher.getInt(PREF_CODE, CODE)
            tokenViewModel.deleteToken(requireContext())
            launchFragment(AuthorizationFragment())
        }
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun setRecyclerViewPayment() {
        adapter = PaymentsAdapter()
        paymentsViewModel.payments.observe(viewLifecycleOwner, Observer { pay ->
            adapter.submitList(pay?.response)
        })
        binding.rvPay.adapter = adapter
    }

    companion object{
        private const val CODE = 0
        private const val PREF_CODE = "code"
    }
}