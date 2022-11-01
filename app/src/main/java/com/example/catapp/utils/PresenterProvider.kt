package com.example.catapp.utils

import com.example.catapp.data.source.remote.CatRemoteDataSource
import com.example.catapp.data.source.remote.LoginRemoteDataSource
import com.example.catapp.data.source.repository.CatRepository
import com.example.catapp.data.source.repository.LoginRepository
import com.example.catapp.view.homescreen.imagescreenpresenter.CatInterface
import com.example.catapp.view.homescreen.imagescreenpresenter.CatPresenter
import com.example.catapp.view.loginscreen.LoginInterface
import com.example.catapp.view.loginscreen.LoginPresenter

object PresenterProvider {

    fun catPresenter(view: CatInterface.View) = CatPresenter(
        CatRepository.getInstance(
            CatRemoteDataSource.getInstance()
        ), view
    )

    fun loginPresenter(view: LoginInterface.View) = LoginPresenter(
        LoginRepository.getInstance(
            LoginRemoteDataSource.getInstance()
        ), view
    )
}
