package com.alijan.fruithubapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.fruithubapp.data.model.Basket
import com.alijan.fruithubapp.databinding.ItemBasketBinding

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketAdapterViewHolder>() {

    private val list = arrayListOf<Basket>()

    inner class BasketAdapterViewHolder(val itemBasketBinding: ItemBasketBinding) :
        RecyclerView.ViewHolder(itemBasketBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterViewHolder {
        val view = ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BasketAdapterViewHolder, position: Int) {
        holder.itemBasketBinding.item = list[position]
        holder.itemBasketBinding.textViewBasketProductTotalCost.text =
            "$${(list[position].productPrice.toDouble() * list[position].basketCount)}"
    }

    fun updateList(newList: MutableList<Basket>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}