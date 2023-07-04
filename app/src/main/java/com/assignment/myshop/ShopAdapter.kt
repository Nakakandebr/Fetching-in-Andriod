package com.assignment.myshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.assignment.myshop.databinding.ProductListItemsBinding

import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class  ShopAdapter (var productList:List<Product>):RecyclerView.Adapter<ShopViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder{
        val binding =
            ProductListItemsBinding .inflate(LayoutInflater.from(parent.context),parent ,false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        var currentProduct =productList[position]
        var  binding=holder.binding
        binding.tvId.text=currentProduct.id.toString()
        binding.tvTitle.text=currentProduct.title.toString()
        binding.tvDescription.text=currentProduct.description.toString()
        binding.tvPrice.text=currentProduct.price.toString()
        binding.tvRating.text=currentProduct.rating.toString()
        binding.tvStock.text=currentProduct.stock.toString()
        binding.tvCategory.text=currentProduct.category.toString()

        Picasso
            .get()
            .load(currentProduct.thumbnail)

            .into(binding.ivThumbnail)

    }
    override fun getItemCount(): Int {
        return productList.size
    }
}
class ShopViewHolder( var binding:ProductListItemsBinding): RecyclerView.ViewHolder(binding.root)
