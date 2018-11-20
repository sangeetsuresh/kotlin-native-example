package com.sangeetsuresh.kotlincommon

import com.sangeetsuresh.kotlincommon.interactor.MainInteractor
import com.sangeetsuresh.kotlincommon.interactor.MainInteractorImpl

interface MainPresenter {
    fun searchTitle(text: String?)
}

class MainPresenterImpl(private val mainView: MainView) : MainPresenter {

    private val mainInteractor: MainInteractor = MainInteractorImpl()

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