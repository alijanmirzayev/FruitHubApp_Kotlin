package com.alijan.fruithubapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.fruithubapp.data.model.Product
import com.alijan.fruithubapp.databinding.ItemProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder>() {

    private val list = arrayListOf<Product>()
    lateinit var onClick: (id: String) -> Unit

    inner class ProductAdapterViewHolder(val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        holder.itemProductBinding.item = list[position]

        holder.itemProductBinding.clItemProduct.setOnClickListener {
            onClick(list[position].id!!)
        }
    }

    fun updateList(newList: List<Product>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}