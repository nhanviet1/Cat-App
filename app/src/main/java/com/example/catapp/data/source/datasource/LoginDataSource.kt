package com.example.catapp.data.source.datasource

import com.example.catapp.data.model.responsemodel.Cat
import com.sun.mvp.data.repository.source.remote.OnResultListener

interface LoginDataSource {

    interface Remote {
        fun getCat(userAPI: String, listener: OnResultListener<MutableList<Cat>>)
    }
}
