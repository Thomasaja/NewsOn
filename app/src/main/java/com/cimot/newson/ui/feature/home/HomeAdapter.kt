package com.cimot.newson.ui.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cimot.newson.data.model.response.news.details.Article
import com.cimot.newson.databinding.ItemListNewsBinding

class HomeAdapter(private val itemClick: (Article) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var items: MutableList<Article> = mutableListOf()

    fun setItems(items: List<Article>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Article>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class HomeViewHolder(private val binding: ItemListNewsBinding, val itemClick: (Article) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Article) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.ivNews.load(image)
                binding.tvTitleNews.text = title
                binding.tvDesc.text = description
            }
        }
    }
}