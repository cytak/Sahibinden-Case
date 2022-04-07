package com.enes.sahibindenenescase.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.databinding.FragmentTweetDetailBinding
import com.enes.sahibindenenescase.databinding.FragmentUserInformationBinding
import com.enes.sahibindenenescase.util.Router
import com.enes.sahibindenenescase.viewmodel.TweetDetailViewModel
import com.enes.sahibindenenescase.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment:Fragment(){
    private lateinit var binding: FragmentUserInformationBinding
    val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_information,container,false)
        binding.viewModel = viewModel
        viewModel.userInformationUiState.observe(viewLifecycleOwner){
            binding.userInformationUiState = it
        }
        viewModel.getUserInformation()
        viewModel.logOut.observe(viewLifecycleOwner){
            Router.popFragment(requireActivity())
            Router.popFragment(requireActivity())
            val bundle = Bundle()
            bundle.putBoolean("signOut",true)
            val fragment = LoginFragment()
            fragment.arguments = bundle
            Router.createFragment(requireActivity(),fragment)
        }
        setListener()
        return binding.root
    }

    private fun setListener(){
        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }
    }

}