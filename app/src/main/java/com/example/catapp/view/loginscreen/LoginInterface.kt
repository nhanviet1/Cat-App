package com.example.catapp.view.loginscreen

import com.example.catapp.data.model.responsemodel.Cat
import java.lang.Exception

interface LoginInterface {
    interface Presenter {
        fun getRemoteCat(userAPI: String)
    }

    interface View {
        fun onGetCatSuccess(cat: MutableList<Cat>)
        fun onError(exception: Exception?)
    }
}
