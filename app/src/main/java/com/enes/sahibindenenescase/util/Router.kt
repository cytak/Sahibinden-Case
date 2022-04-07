package com.enes.sahibindenenescase.util

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.view.fragments.LoginFragment
import com.enes.sahibindenenescase.view.fragments.TweetDetailFragment
import com.enes.sahibindenenescase.view.fragments.TweetFragment

class Router {
    companion object {
        private var fragment: Fragment? = null

        fun createFragment(context: Context, fragment: Fragment) {
            this.fragment = fragment
                if (fragment !is LoginFragment){
                    (context as FragmentActivity).supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .add(R.id.frmMain, fragment)
                        .setReorderingAllowed(true)
                        .addToBackStack(fragment.javaClass.simpleName)
                        .commit()
                }else{
                    (context as FragmentActivity).supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.frmMain, fragment)
                        .setReorderingAllowed(true)
                        .commit()
                }

        }

        fun popFragment(context: Context) {
            if (context is FragmentActivity) {
                if (context.supportFragmentManager.backStackEntryCount == 1 || fragment is LoginFragment)
                    context.finish()
                else{
                    context.supportFragmentManager.popBackStack()
                }
            }
        }


    }


}