package com.alijan.fruithubapp.ui.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.fruithubapp.databinding.FragmentSplashBinding
import com.alijan.fruithubapp.ui.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun layoutInflater(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
        }
    }

}