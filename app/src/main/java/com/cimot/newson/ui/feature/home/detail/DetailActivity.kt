package com.cimot.newson.ui.feature.home.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import com.cimot.newson.R
import com.cimot.newson.base.arch.BaseActivity
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.local.room.entity.FavoriteNews
import com.cimot.newson.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity :
    BaseActivity<ActivityDetailsBinding, DetailViewModel>(ActivityDetailsBinding::inflate),
    DetailContract.View {

    private lateinit var newsFavorite: FavoriteNews
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
    }

    companion object {
        const val EXTRAS_NEWS_DETAIL = "EXTRAS_NEWS_DETAIL"

        @JvmStatic
        fun startActivity(context: Context?, newsId: String?) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRAS_NEWS_DETAIL, newsId)
            context?.startActivity(intent)
        }
    }

    override fun observeData() {
        getViewModel().getNewsDetailResponse().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    it.data?.let { data ->
                        setContentData(data)
                        addNewsToFavorite(data)
                    }
                    showError(false, null)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
        getViewModel().getNewsId().observe(this) {
            it?.let {
                getViewModel().getNewsDetail(it)
            }

        }
    }


    override fun setContentData(data: Article) {
        getViewBinding().ivDetailNews.load(data.image)
        getViewBinding().tvDetailTitleNews.text = data.title
        getViewBinding().tvDetailDesc.text = data.description
    }

    override fun getIntentData() {
        getViewModel().setIntentData(intent.extras)
    }

    private fun addNewsToFavorite(detailNews: Article) {
        getViewBinding().ivBtnFavorite.setOnClickListener {
            getViewBinding().ivBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite)
            isFavorite = !isFavorite
            if (isFavorite) {
                newsFavorite =
                    FavoriteNews(
                        detailNews.title,
                        detailNews.image,
                        detailNews.description
                    )
            }
            newsFavorite.let {
                getViewModel().insertNewsFavorite(it)
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().svDetailNews.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        if (isErrorEnabled){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initView() {
       getIntentData()
    }
}