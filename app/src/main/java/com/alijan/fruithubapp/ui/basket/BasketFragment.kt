package com.alijan.fruithubapp.ui.basket

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alijan.fruithubapp.data.model.Basket
import com.alijan.fruithubapp.databinding.FragmentBasketBinding
import com.alijan.fruithubapp.ui.BaseFragment
import com.alijan.fruithubapp.ui.adapters.BasketAdapter
import com.alijan.fruithubapp.ui.home.HomeViewModel

class BasketFragment : BaseFragment<FragmentBasketBinding>() {
    private val adapter = BasketAdapter()

    override fun layoutInflater(): FragmentBasketBinding {
        return FragmentBasketBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        val homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding.rvBasket.adapter = adapter
        homeViewModel.basket.value?.let {
            adapter.updateList(it)
            binding.textViewBasketTotalCost.text = "$${calcAllBasketCost(it)}"
        }
        binding.imageViewLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun calcAllBasketCost(basket: MutableList<Basket>): Double {
        var sum: Double = 0.00
        basket.forEach {
            sum += it.basketCount * it.productPrice.toDouble()
        }
        return sum
    }

}