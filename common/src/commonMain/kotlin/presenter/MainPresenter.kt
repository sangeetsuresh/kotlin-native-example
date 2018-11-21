package com.sangeetsuresh.kotlincommon.presenter

import com.sangeetsuresh.kotlincommon.view.MainView
import com.sangeetsuresh.kotlincommon.repository.MainRepository
import com.sangeetsuresh.kotlincommon.repository.MainRepositoryImpl

interface MainPresenter {
    fun searchTitle(text: String?)
}

class MainPresenterImpl(private val mainView: MainView) : MainPresenter {

    private val mainInteractor: MainRepository = MainRepositoryImpl()

    override fun searchTitle(text: String?) {
        mainView.showProgress()
        if (text != null && text.length > 3) {
            mainInteractor.searchTitle(text) {
                mainView.hideProgress()
                if (it?.data == null) {
                    mainView.showNoItem()
                } else {
                    mainView.loadList(it)
                }
            }
        } else {
            mainView.hideProgress()
        }
    }
}