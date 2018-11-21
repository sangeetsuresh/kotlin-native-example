package com.sangeetsuresh.kotlincommon.view

import com.sangeetsuresh.kotlincommon.model.SearchResult

interface MainView{
    fun loadList(search: SearchResult)
    fun showNoItem()
    fun showProgress()
    fun hideProgress()
}