package com.example.catapp.view.loginscreen

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.repository.LoginRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class LoginPresenter(
    private val loginRepository: LoginRepository,
    private val view: LoginInterface.View
) : LoginInterface.Presenter {

    override fun getRemoteCat(userAPI: String) {
        loginRepository.getCat(userAPI, object : OnResultListener<MutableList<Cat>> {
            override fun onSuccess(data: MutableList<Cat>) {
                view.onGetCatSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }

        })
    }
}
