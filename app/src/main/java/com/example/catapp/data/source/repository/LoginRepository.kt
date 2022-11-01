package com.example.catapp.data.source.repository

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.datasource.LoginDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class LoginRepository(
    private val remote: LoginDataSource.Remote,
) : LoginDataSource.Remote {

    override fun getCat(
        userAPI: String, listener: OnResultListener<MutableList<Cat>>
    ) {
        remote.getCat(userAPI, listener)
    }

    companion object {
        private var instance: LoginRepository? = null

        fun getInstance(remote: LoginDataSource.Remote) = synchronized(this) {
            instance ?: LoginRepository(remote).also { instance = it }
        }
    }
}
