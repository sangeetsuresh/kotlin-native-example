package com.sangeetsuresh.kotlincommon

import com.sangeetsuresh.kotlincommon.model.SearchResult

interface MainView{
    fun loadList(search: SearchResult)
    fun showNoItem()
    fun showProgress()
    fun hideProgress()
}