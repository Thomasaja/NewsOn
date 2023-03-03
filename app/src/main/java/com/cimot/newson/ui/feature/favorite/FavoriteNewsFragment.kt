package com.cimot.newson.ui.feature.favorite

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cimot.newson.R
import com.cimot.newson.base.arch.BaseFragment
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.local.room.entity.FavoriteNews
import com.cimot.newson.databinding.FragmentFavoriteNewsBinding
import com.cimot.newson.ui.feature.favorite.adapter.FavoriteNewsAdapter
import com.cimot.newson.ui.feature.home.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteNewsFragment :
    BaseFragment<FragmentFavoriteNewsBinding, FavoriteNewsViewModel>(FragmentFavoriteNewsBinding::inflate),
    FavoriteNewsContract.View {

    private lateinit var adapter: FavoriteNewsAdapter
    private var listFavNews: List<FavoriteNews>? = null

    override fun initView() {
        getViewBinding().etSearchFavoriteNews.addTextChangedListener { searchQuery ->
            searchQuery?.let {
                if (searchQuery.toString().isNotEmpty()) {
                    getSearchData(searchQuery.toString())
                }
            }
        }
        getListData()
    }

    override fun initList() {
        adapter = FavoriteNewsAdapter {
            DetailActivity.startActivity(context, it.title)
        }
        getViewBinding().rvFavoriteNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FavoriteNewsFragment.adapter
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val favNews = listFavNews?.get(position)
                favNews?.let { getViewModel().deleteFavoriteNews(it) }
                Snackbar.make(
                    requireView(),
                    getString(R.string.success_deleted_fav_news),
                    Snackbar.LENGTH_SHORT
                ).show()
                getListData()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(getViewBinding().rvFavoriteNews)
        }
    }

    override fun getListData() {
        getViewModel().getAllNews()
    }

    override fun getSearchData(searchQuery: String) {
        val query = "%$searchQuery%"
        getViewModel().searchFavoriteNews(query)
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getNewsListLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }

                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    showError(false, null)
                    initList()
                    setDataAdapter(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    private fun setDataAdapter(data: List<FavoriteNews>?) {
        data?.let {
            adapter.setItems(it)
            listFavNews = it
        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvFavoriteNews.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        msg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}