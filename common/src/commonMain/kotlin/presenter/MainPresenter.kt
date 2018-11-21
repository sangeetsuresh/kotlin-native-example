package com.sangeetsuresh.kotlincommon

import com.sangeetsuresh.kotlincommon.interactor.MainRepository
import com.sangeetsuresh.kotlincommon.interactor.MainRepositoryImpl

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