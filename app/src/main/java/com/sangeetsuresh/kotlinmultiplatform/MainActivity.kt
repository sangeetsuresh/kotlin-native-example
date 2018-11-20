package com.sangeetsuresh.kotlinmultiplatform

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sangeetsuresh.kotlincommon.MainPresenter
import com.sangeetsuresh.kotlincommon.MainPresenterImpl
import com.sangeetsuresh.kotlincommon.MainView
import com.sangeetsuresh.kotlincommon.model.SearchResult
import kotlinx.android.synthetic.main.activity_main.et_search_movies
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_main.rv_movies
import kotlinx.android.synthetic.main.activity_main.tv_no_items

private const val TYPING_TIME = 1000L

class MainActivity : AppCompatActivity(), MainView {
    val mainPresenter: MainPresenter by lazy(LazyThreadSafetyMode.NONE) {
        MainPresenterImpl(this)
    }

    val handler = Handler()
    val runnable = Runnable {
        mainPresenter.searchTitle(et_search_movies.text.toString())
    }

    override fun hideProgress() {
        progress_bar.visibility = View.INVISIBLE
    }

    override fun showProgress() {
        rv_movies.adapter = null
        tv_no_items.visibility = View.INVISIBLE
        progress_bar.visibility = View.VISIBLE
    }

    override fun loadList(search: SearchResult) {
        rv_movies.adapter = MoviesAdapter(search)
    }

    override fun showNoItem() {
        tv_no_items.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_movies.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.margin).toInt()))
        et_search_movies.addTextChangedListener(AfterTextChangedListener {
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed(runnable, TYPING_TIME)
        })
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
