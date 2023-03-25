package com.example.carzone.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carzone.R
import com.example.carzone.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding

    var redirectionUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiData()
        initView()
    }

    private fun intiData() {
        redirectionUrl = intent.getStringExtra(REDIRECTION_URL)
        if (redirectionUrl.isNullOrEmpty())
            redirectionUrl = DEFAULT_URL
    }

    private fun initView() {
        redirectionUrl?.let { binding.webView.loadUrl(it) }
    }

    companion object {
        const val REDIRECTION_URL = "redirection_url"
        const val DEFAULT_URL = "https://www.cartrade.com/new-cars/best-luxury-cars-in-india/" // default url when redirection url is empty or null

        fun getIntent(context: Context, redirectionUrl: String): Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(REDIRECTION_URL, redirectionUrl)
            }
        }
    }
}