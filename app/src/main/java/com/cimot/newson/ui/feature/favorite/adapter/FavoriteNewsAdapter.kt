package com.cimot.newson.ui.feature.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cimot.newson.data.local.room.entity.FavoriteNews
import com.cimot.newson.databinding.ItemListFavoriteNewsBinding

class FavoriteNewsAdapter(private val itemClick: (FavoriteNews) -> Unit) :
    RecyclerView.Adapter<FavoriteNewsAdapter.FavoriteNewsViewHolder>() {

    private var items: MutableList<FavoriteNews> = mutableListOf()

    fun setItems(items: List<FavoriteNews>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    private fun addItems(items: List<FavoriteNews>) {
        this.items.addAll(items)
    }

    private fun clearItems() {
        this.items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewsViewHolder {
        val binding =
            ItemListFavoriteNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteNewsViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FavoriteNewsViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class FavoriteNewsViewHolder(
        private val binding: ItemListFavoriteNewsBinding,
        val itemClick: (FavoriteNews) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: FavoriteNews) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.ivFavoriteNews.load(image)
                binding.tvTitleFavoriteNews.text = title
                binding.tvTypeFavoriteNews.text = description
            }
        }
    }
}