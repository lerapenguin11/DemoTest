package com.example.demotest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.demotest.databinding.FragmentPaymentsBinding
import com.example.demotest.presentation.adapter.PaymentsAdapter
import com.example.demotest.viewmodel.AuthorizationViewModel
import com.example.demotest.viewmodel.PaymentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentsFragment : Fragment() {
    private var _binding : FragmentPaymentsBinding? = null
    private val binding get() = _binding!!
    private val paymentsViewModel by viewModel<PaymentsViewModel>()
    private val tokenViewModel by viewModel<AuthorizationViewModel>()
    private lateinit var adapter : PaymentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)

        tokenViewModel.decryptToken(requireContext())?.let { paymentsViewModel.getPayments(it) }
        setRecyclerViewPayment()
        return binding.root
    }

    private fun setRecyclerViewPayment() {
        adapter = PaymentsAdapter()
        paymentsViewModel.payments.observe(viewLifecycleOwner, Observer { pay ->
            adapter.submitList(pay?.response)
        })
        binding.rvPay.adapter = adapter
    }
}