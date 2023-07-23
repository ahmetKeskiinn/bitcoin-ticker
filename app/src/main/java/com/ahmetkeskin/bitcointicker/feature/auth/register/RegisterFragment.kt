package com.ahmetkeskin.bitcointicker.feature.auth.register

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
                    //auth()
                }
            }
        }
    }

    private fun initClickListener() {
        binding.login.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}