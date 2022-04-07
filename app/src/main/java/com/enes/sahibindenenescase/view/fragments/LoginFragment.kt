package com.enes.sahibindenenescase.view.fragments

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.databinding.FragmentLoginBinding
import com.enes.sahibindenenescase.util.Constants
import com.enes.sahibindenenescase.util.Router
import com.enes.sahibindenenescase.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by viewModels()
    lateinit var twitterDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        if (arguments?.getBoolean("signOut",false) == true)
            signOut()
        viewModel.liveDataUrl.observe(viewLifecycleOwner) { url ->
            setupTwitterWebViewDialog(url)
        }

        return binding.root
    }

    private fun setupTwitterWebViewDialog(url: String) {
        twitterDialog = Dialog(requireContext())
        val webView = WebView(requireContext())
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = TwitterWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        twitterDialog.setContentView(webView)
        twitterDialog.show()
    }

    @Suppress("OverridingDeprecatedMember")
    inner class TwitterWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?, ): Boolean {
            if (request?.url.toString().startsWith(Constants.CALLBACK_URL)) {
                handleUrl(request?.url.toString())
                if (request?.url.toString().contains(Constants.CALLBACK_URL)) {
                    twitterDialog.dismiss()
                }
                return true
            }
            return false
        }


        private fun handleUrl(url: String) {
            val uri = Uri.parse(url)
            val oauthVerifier = uri.getQueryParameter("oauth_verifier") ?: ""
            viewModel.viewModelScope.launch {
                val accToken = withContext(Dispatchers.IO) {
                    viewModel.twitter.getOAuthAccessToken(oauthVerifier)
                }
                val usr = withContext(Dispatchers.IO) {
                    viewModel.twitter.verifyCredentials()
                }
                withContext(Dispatchers.IO) {
                    viewModel.saveUserData(accToken, usr)
                }
            }
            Router.createFragment(requireActivity(),TweetFragment())
        }

    }
    private fun signOut(){
        CookieManager.getInstance().removeAllCookie();
    }

}

