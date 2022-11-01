package com.example.catapp.data.source.remote

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.datasource.LoginDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener
import com.example.catapp.data.source.remote.fetchjson.GetJson
import com.example.catapp.utils.BASE_URL_LOGIN

class LoginRemoteDataSource : LoginDataSource.Remote {

    override fun getCat(userAPI: String, listener: OnResultListener<MutableList<Cat>>) {
        GetJson(
            urlString = BASE_URL_LOGIN,
            keyEntity = "",
            userAPI = userAPI,
            listener = listener
        )
    }

    companion object {
        private var instance: LoginRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: LoginRemoteDataSource().also { instance = it }
        }
    }
}
