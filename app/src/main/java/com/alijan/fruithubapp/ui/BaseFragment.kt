package com.alijan.fruithubapp.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alijan.fruithubapp.databinding.LoadingBinding

abstract class BaseFragment<_ViewBinding : ViewBinding>() : Fragment() {
    private var _binding: _ViewBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = layoutInflater()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observerViewModel()
    }

    protected abstract fun layoutInflater(): _ViewBinding
    protected abstract fun setupUI()
    open fun observerViewModel() {}

    protected fun createLoadingDialog(): Dialog {
        val dialog = Dialog(requireContext())
        val dialogBinding = LoadingBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}