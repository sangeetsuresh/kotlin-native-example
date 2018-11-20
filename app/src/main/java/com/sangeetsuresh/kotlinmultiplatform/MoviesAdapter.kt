package com.sangeetsuresh.kotlinmultiplatform

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.sangeetsuresh.kotlincommon.model.SearchItem
import com.sangeetsuresh.kotlincommon.model.SearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item.view.im_poster

class MoviesAdapter(private val searchResult: SearchResult) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        view.im_poster.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.im_poster.viewTreeObserver.removeOnGlobalLayoutListener(this)
                view.im_poster.layoutParams.height = (view.width * 1.67).toInt()
            }
        })
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = searchResult.data?.size ?: 0

    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {
        viewHolder.onBind(searchResult.data!![position])
    }

    class MoviesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(searchItem: SearchItem) {
            Picasso.get().load(searchItem.poster).into(view.im_poster)
        }

    }
}