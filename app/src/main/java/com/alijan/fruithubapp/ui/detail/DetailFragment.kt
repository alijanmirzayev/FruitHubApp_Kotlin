package com.alijan.fruithubapp.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.fruithubapp.data.model.Basket
import com.alijan.fruithubapp.data.source.remote.BaseResponse
import com.alijan.fruithubapp.databinding.FragmentDetailBinding
import com.alijan.fruithubapp.ui.BaseFragment
import com.alijan.fruithubapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val viewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()

    override fun layoutInflater(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        viewModel.getProductById(args.id)

        binding.imageViewDetailLeft.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.viewDetailBasketCountIncrement.setOnClickListener {
            viewModel.increment()
        }

        binding.imageViewDetailBasketCountDecrement.setOnClickListener {
            if (viewModel.productBasketCount.value != 1) {
                viewModel.decrement()
            }
        }

        binding.buttonDetailAddToBasket.setOnClickListener {
            val homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
            val item = viewModel.basketItem()
            homeViewModel.addItemToBasket(item)
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()

        viewModel.product.observe(viewLifecycleOwner) { response ->
            when (response) {
                is BaseResponse.Error -> Toast.makeText(
                    context,
                    "Error: ${response.message}",
                    Toast.LENGTH_SHORT
                ).show()

                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    binding.item = response.data
                }
            }
        }

        viewModel.productBasketCount.observe(viewLifecycleOwner) {
            binding.textViewDetailBasketCount.text = it.toString()
        }
    }

}