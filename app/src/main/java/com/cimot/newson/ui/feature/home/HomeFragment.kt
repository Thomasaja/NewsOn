package com.cimot.newson.ui.feature.home

import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.cimot.newson.base.arch.BaseFragment
import com.cimot.newson.base.model.Resource
import com.cimot.newson.databinding.FragmentHomeBinding
import com.cimot.newson.ui.feature.home.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment:BaseFragment<FragmentHomeBinding,HomeViewModel>(FragmentHomeBinding::inflate),HomeContract.View {

    private lateinit var adapter: HomeAdapter
    override fun initView() {
        getData()
    }

    override fun initList() {
        adapter = HomeAdapter {
            DetailActivity.startActivity(requireContext(), it.id)
        }

        getViewBinding().rvNews.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = this@HomeFragment.adapter
        }
    }

    override fun getData() {
        getViewModel().getAllNews()
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvNews.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().tvMessage.isVisible  = isErrorEnabled
        getViewBinding().tvMessage.text = msg
    }

    override fun observeData() {
        getViewModel().getNewsLiveData().observe(this){
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
                    setAdapter(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    private fun setAdapter(data: List<Article>?) {
        data?.let { adapter.setItems(it) }
    }
}