package com.alijan.fruithubapp.ui.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.fruithubapp.databinding.FragmentSplashBinding
import com.alijan.fruithubapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val viewModel by viewModels<SplashViewModel>()
    private var isEnterName: Boolean = false
    override fun layoutInflater(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        lifecycleScope.launch {
            delay(2000)
            if (isEnterName) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
            }
        }
    }

    override fun observerViewModel() {
        viewModel.name.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                isEnterName = true
            } else {
                isEnterName = false
            }
        }
    }

}