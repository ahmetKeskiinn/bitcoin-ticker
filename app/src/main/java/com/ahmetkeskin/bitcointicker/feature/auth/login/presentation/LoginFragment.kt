package com.ahmetkeskin.bitcointicker.feature.auth.login.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.components.TickerButtonClicked
import com.ahmetkeskin.bitcointicker.components.buttonattributes.ButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.Gravity
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonTextAttributes
import com.ahmetkeskin.bitcointicker.databinding.FragmentLoginBinding
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
                    //auth()
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

}