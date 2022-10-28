package com.example.catapp.view


import android.os.Bundle
import com.example.catapp.databinding.ActivityWebViewBinding
import com.example.catapp.utils.CAT_URL
import com.example.catapp.utils.base.BaseActivity

class WebViewActivity : BaseActivity<ActivityWebViewBinding>(ActivityWebViewBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openWebView()
    }

    private fun openWebView() {
        binding.webViewSignup.run {
            settings.javaScriptEnabled = true
            webChromeClient
            loadUrl(CAT_URL)
        }
    }
}
