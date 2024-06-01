package com.alijan.fruithubapp.ui.ordercomplete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alijan.fruithubapp.R
import com.alijan.fruithubapp.databinding.FragmentOrderCompleteBinding
import com.alijan.fruithubapp.ui.BaseFragment

class OrderCompleteFragment : BaseFragment<FragmentOrderCompleteBinding>() {
    override fun layoutInflater(): FragmentOrderCompleteBinding {
        return FragmentOrderCompleteBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        binding.buttonContinueShopping.setOnClickListener {
            findNavController().navigate(OrderCompleteFragmentDirections.actionOrderCompleteFragmentToHomeFragment())
        }
    }

}