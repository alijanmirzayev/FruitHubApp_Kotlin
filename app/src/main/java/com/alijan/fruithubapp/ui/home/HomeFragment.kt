package com.alijan.fruithubapp.ui.home

import android.text.Html
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.fruithubapp.data.api.BaseResponse
import com.alijan.fruithubapp.databinding.FragmentHomeBinding
import com.alijan.fruithubapp.ui.BaseFragment
import com.alijan.fruithubapp.ui.adapters.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by activityViewModels<HomeViewModel>()
    private val recommendAdapter = ProductAdapter()

    override fun layoutInflater(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun setupUI() {

        binding.rvHomeRecommend.adapter = recommendAdapter
        recommendAdapter.onClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
                )
            )
        }

        binding.imageViewHomeBasket.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBasketFragment())
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        val dialog = createLoadingDialog()

        viewModel.products.observe(viewLifecycleOwner) { response ->
            when (response) {
                is BaseResponse.Error -> {
                    Toast.makeText(
                        context,
                        "Error: ${response.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }
                is BaseResponse.Loading -> {
                    dialog.show()
                }
                is BaseResponse.Success -> {
                    recommendAdapter.updateList(response.data!!)
                    dialog.dismiss()
                }
            }
        }

        viewModel.name.observe(viewLifecycleOwner) { response ->
            val text = "Hello ${response}, <b>What fruit salad combo do you want today?</b>"
            binding.textViewHomeTitle.setText(Html.fromHtml(text))
        }
    }
}