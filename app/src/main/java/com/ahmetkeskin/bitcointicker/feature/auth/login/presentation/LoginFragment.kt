package com.ahmetkeskin.bitcointicker.feature.auth.login.presentation

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ahmetkeskin.bitcointicker.MainActivity
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.components.TickerButtonClicked
import com.ahmetkeskin.bitcointicker.components.buttonattributes.ButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.Gravity
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonTextAttributes
import com.ahmetkeskin.bitcointicker.databinding.FragmentLoginBinding
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    layoutId = R.layout.fragment_login
) {
    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[LoginViewModel::class.java]
        }
        initUI()
    }

    private fun initUI() {
        initContinueButton()
        initRegisterButton()
    }

    private fun initContinueButton() {
        binding.continueButton.apply {
            setButton(
                context?.getDrawable(R.drawable.bg_ticker_button)?.let {
                    TickerButtonAttributes(
                        background = it,
                        buttonAttributes = ButtonAttributes(
                            tickerButtonTextAttributes =
                            TickerButtonTextAttributes(
                                text = getString(R.string.button_continue),
                                textColor = context.getColor(R.color.white),
                                textGravity = Gravity.CENTER
                            ),
                        )
                    )
                }
            )
            listener = object : TickerButtonClicked {
                override fun isButtonClicked(status: Boolean) {
                    prepareAuth()
                }
            }
        }
    }

    private fun initRegisterButton() {
        binding.signUpWithEmailButton.apply {
            setButton(
                context?.getDrawable(R.drawable.bg_button_sign_up)?.let {
                    TickerButtonAttributes(
                        background = it,
                        buttonAttributes = ButtonAttributes(
                            tickerButtonTextAttributes =
                            TickerButtonTextAttributes(
                                text = getString(R.string.sign_up_with_email),
                                textColor = context.getColor(R.color.white),
                                textGravity = Gravity.CENTER
                            ),
                        )
                    )
                }
            )
            setButtonBg(R.drawable.bg_button_sign_up)
            listener = object : TickerButtonClicked {
                override fun isButtonClicked(status: Boolean) {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_loginFragment_to_registerFragment)
                }
            }
        }
    }

    private fun prepareAuth() {
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                auth(email, password)
            }
        }
    }

    private fun auth(email: String, password: String) {
        showProgress()
        val userModel = UserModel(
            email = email,
            password = password
        )
        viewModel.authentication(
            userModel
        ).observe(viewLifecycleOwner, Observer {
            if (it == true) {
                saveUserSettings(userModel)
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            hideProgress()
        })
    }

    private fun saveUserSettings(userModel: UserModel) {
        viewModel.saveUserSettings(userModel)
    }
}