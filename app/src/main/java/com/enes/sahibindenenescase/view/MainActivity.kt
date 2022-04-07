package com.enes.sahibindenenescase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.databinding.ActivityMainBinding
import com.enes.sahibindenenescase.util.Router
import com.enes.sahibindenenescase.view.fragments.LoginFragment
import com.enes.sahibindenenescase.view.fragments.TweetFragment
import com.enes.sahibindenenescase.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.loginControl.observe(this){loginControl->
            if (loginControl){
                Router.createFragment(context = this,TweetFragment())
            }else{
                Router.createFragment(context = this,LoginFragment())
            }
        }
        viewModel.getUserData()
    }

    override fun onBackPressed() {
        Router.popFragment(context = this)
    }
}