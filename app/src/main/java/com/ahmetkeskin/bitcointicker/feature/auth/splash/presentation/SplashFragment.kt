package com.ahmetkeskin.bitcointicker.feature.auth.splash.presentation

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ahmetkeskin.bitcointicker.MainActivity
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.databinding.FragmentSplashBinding
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    layoutId = R.layout.fragment_splash
) {
    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[SplashViewModel::class.java]
        }
        initUI()
    }

    private fun initUI() {
        viewModel.getUserSettings().observe(
            viewLifecycleOwner,
            Observer {
                if (it.password != null || it.email != null) {
                    changeFlowForAuthentication(UserModel(email = it.email, password = it.password))
                } else {
                    goLogin()
                }
            }
        )
    }

    private fun changeFlowForAuthentication(userModel: UserModel) {
        viewModel.auth(userModel).observe(
            viewLifecycleOwner,
            Observer {
                if (it == true) {
                    goHome()
                } else {
                    goLogin()
                }
            }
        )
    }

    private fun goLogin() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun goHome() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
