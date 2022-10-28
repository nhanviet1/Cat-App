package com.example.catapp.view

import android.content.Intent
import android.os.Bundle
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.repository.CatRepository
import com.example.catapp.utils.base.BaseActivity
import com.example.catapp.view.presenter.CatInterface
import com.example.catapp.view.presenter.CatPresenter
import com.sun.mvp.data.repository.source.local.CatLocalDataSource
import com.example.catapp.data.source.remote.CatRemoteDataSource
import com.example.catapp.databinding.ActivityLoginBinding
import com.example.catapp.utils.shortToast
import java.lang.Exception

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    CatInterface.View {

    private var catPresenter: CatInterface.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        bindingButton()
    }

    private fun setup() {
        catPresenter = CatPresenter(
            CatRepository.getInstance(
                CatRemoteDataSource.getInstance(),
                CatLocalDataSource.getInstance()
            ), this@LoginActivity
        )
    }

    override fun onGetCatSuccess(cat: MutableList<Cat>) {
        val checker = cat[0].breed
        if (checker == null) {
            shortToast("Your API is invalid!")
        } else {
            shortToast("Welcome!")
        }
    }

    private fun bindingButton() {
        binding.buttonLogin.setOnClickListener {
            val userAPI = binding.editTextRequestAPI.text.toString()
            if (userAPI != "") {
                catPresenter?.getRemoteCat(userAPI)
            } else {
                shortToast("Please enter your API")
            }
        }

        binding.textOpenWebView.setOnClickListener {
            val intent = Intent(this@LoginActivity, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onError(exception: Exception?) {
        TODO("Not yet implemented")
    }
}
