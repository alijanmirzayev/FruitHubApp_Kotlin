package com.alijan.fruithubapp.ui.welcome

import androidx.navigation.fragment.findNavController
import com.alijan.fruithubapp.databinding.FragmentWelcomeBinding
import com.alijan.fruithubapp.ui.BaseFragment

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {

    override fun layoutInflater(): FragmentWelcomeBinding {
        return FragmentWelcomeBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        binding.buttonWelcomeSubmit.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToAuthenticationFragment())
        }
    }
}