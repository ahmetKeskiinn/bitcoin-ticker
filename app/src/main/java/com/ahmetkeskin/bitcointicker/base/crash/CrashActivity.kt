package com.ahmetkeskin.bitcointicker.base.crash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.components.TickerButtonClicked
import com.ahmetkeskin.bitcointicker.components.buttonattributes.ButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.Gravity
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonTextAttributes
import com.ahmetkeskin.bitcointicker.databinding.ActivityCrashBinding
import com.ahmetkeskin.bitcointicker.feature.auth.AuthActivity

class CrashActivity : AppCompatActivity() {
    private var binding: ActivityCrashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initUI()
        supportActionBar?.hide()
        window?.statusBarColor = getColor(R.color.black)
    }

    private fun initUI() {
        binding?.crashButton?.apply {
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
                    goSplash()
                }
            }
        }
    }

    private fun goSplash() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}