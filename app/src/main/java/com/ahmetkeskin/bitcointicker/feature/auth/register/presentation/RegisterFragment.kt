package com.ahmetkeskin.bitcointicker.feature.auth.register.presentation

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.components.TickerButtonClicked
import com.ahmetkeskin.bitcointicker.components.buttonattributes.ButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.Gravity
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonTextAttributes
import com.ahmetkeskin.bitcointicker.databinding.FragmentRegisterBinding
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    layoutId = R.layout.fragment_register
) {
    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[RegisterViewModel::class.java]
        }
        initUI()
        initClickListener()
    }

    private fun initUI() {
        binding.registerButton.apply {
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
                    setRegister()
                    //auth()
                }
            }
        }
    }

    private fun setRegister() {
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (password.isNotEmpty() && checkEmail(email)) {
            register(
                UserModel(
                    email,
                    password
                )
            )
        }
    }

    private fun register(userModel: UserModel) {
        showProgress()
        viewModel.register(
            userModel
        ).observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                showToast(getString(R.string.something_went_wrong))
            }
            hideProgress()
        })
    }

    private fun checkEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            showToast(getString(R.string.empty_email))
            false
        } else if (!email.contains("@")) {
            showToast(getString(R.string.email_pattern))
            false
        } else if (email.indexOf("@") == 0 || email.indexOf("@") == email.length - 1) {
            showToast(getString(R.string.email_pattern))
            false
        } else {
            true
        }
    }

    private fun initClickListener() {
        binding.login.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}