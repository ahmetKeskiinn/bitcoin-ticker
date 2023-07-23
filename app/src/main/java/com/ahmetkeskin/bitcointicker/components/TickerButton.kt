package com.ahmetkeskin.bitcointicker.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.extensions.toDp
import com.ahmetkeskin.bitcointicker.base.extensions.toPx
import com.ahmetkeskin.bitcointicker.components.buttonattributes.Gravity
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonAttributes

class TickerButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    @StyleRes
    private var mTextStyle: Int = R.style.TextStyleB16L
    private var mTextColor: Int = -1
    private val ELEVATION = 1.toPx().toFloat()
    private var mTextSize: Int = -1
    private var mButtonBackground: Drawable =
        ContextCompat.getDrawable(context, R.drawable.bg_ticker_button)!!
    private var mTitle = "Title"
    private var includePadding = true
    private var RATIO = 0.7368f
    private var clickStatus = false
    var listener: TickerButtonClicked? = null

    init {
        View.inflate(context, R.layout.component_ticker_button, this)
        attributeSet?.let {
            val attrs = context.obtainStyledAttributes(it, R.styleable.TickerButton)
            try {
                mTextStyle = attrs.getResourceId(
                    R.styleable.TickerButton_button_textStyle,
                    R.style.TextStyleB16L
                )
                mTextSize = attrs.getDimensionPixelSize(R.styleable.TickerButton_text_size, -1)
                mButtonBackground = attrs.getDrawable(R.styleable.TickerButton_button_background)
                    ?: ContextCompat.getDrawable(context, R.drawable.bg_ticker_button)!!
                includePadding =
                    attrs.getBoolean(R.styleable.TickerButton_text_include_padding, true)
                if (!includePadding) RATIO = 0f
                setupView()
            } finally {
                attrs.recycle()
            }
        }
    }

    @SuppressLint("NewApi")
    private fun setupView() {
        elevation = ELEVATION
//        TextViewCompat.setTextAppearance(findViewById<AppCompatTextView>(findViewById(R.id.ak_button)), mTextStyle)
        background = mButtonBackground
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            androidx.appcompat.R.attr.selectableItemBackground,
            outValue,
            true
        )
        foreground = ContextCompat.getDrawable(context, R.drawable.bg_button_ripple)
        if (mTextSize != -1) findViewById<AppCompatTextView>(R.id.ak_button).textSize =
            mTextSize.toDp().toFloat()
        setPadding((findViewById<AppCompatTextView>(R.id.ak_button).textSize * RATIO).toInt())
    }

    fun setButton(
        model: TickerButtonAttributes?
    ) {
        model?.let {
            // mButtonBackground = background
            it.buttonAttributes?.let { buttonAttributes ->
                buttonAttributes.tickerButtonTextAttributes?.let { textAttributes ->
                    setButtonText(
                        findViewById<AppCompatTextView>(R.id.ak_button),
                        textAttributes.text,
                        textAttributes.textColor
                    )
                    textAttributes.textGravity?.let { gravity ->
                        setViewLocation(findViewById(R.id.ak_button), gravity)
                    }
                }
                setViewBackground(findViewById(R.id.ak_button), it.background)
                buttonAttributes.buttonImage?.let { bg ->
                    setViewBackground(findViewById(R.id.ak_button_image), bg)
                }
                if (buttonAttributes.buttonImageGravity != null) {
                    // setViewLocation(ak_button_image, buttonAttributes.buttonImageGravity)
                }
            }
            setListener(clickStatus)
        }
        setupView()
    }

    private fun setViewLocation(
        view: View,
        gravity: Gravity,
        nearView: View? = findViewById<ConstraintLayout>(R.id.root)
    ) {
        view.updateLayoutParams<ConstraintLayout.LayoutParams> {
            nearView?.let { nearView ->
                when (gravity) {
                    Gravity.START -> {
                        this.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                        this.endToEnd = ConstraintLayout.LayoutParams.UNSET
                    }
                    Gravity.END -> {
                        this.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                        this.startToStart = ConstraintLayout.LayoutParams.UNSET
                    }
                    Gravity.CENTER -> {
                        this.startToStart = findViewById<ConstraintLayout>(R.id.root).id
                        this.endToEnd = findViewById<ConstraintLayout>(R.id.root).id
                    }
                    Gravity.CENTER_BOTTOM -> {
                        this.startToStart = nearView.id
                        this.endToEnd = nearView.id
                        this.topToBottom = nearView.id
                    }
                    Gravity.CENTER_TOP -> {
                        this.startToStart = nearView.id
                        this.endToEnd = nearView.id
                        this.bottomToTop = nearView.id
                    }
                    Gravity.CENTER_LEFT_NEAR -> {
                        this.endToEnd = ConstraintLayout.LayoutParams.UNSET
                        this.endToStart = findViewById<AppCompatTextView>(R.id.ak_button).id
                    }
                    Gravity.CENTER_RIGHT_NEAR -> {
                        this.startToEnd = findViewById<AppCompatTextView>(R.id.ak_button).id
                        this.endToEnd = ConstraintLayout.LayoutParams.UNSET
                    }
                }
            }
        }
    }

    private fun setListener(clickStatus: Boolean) {
        this.setOnClickListener {
            listener?.isButtonClicked(status = clickStatus)
            this.clickStatus = !clickStatus
        }
    }

    private fun setViewBackground(view: View, drawable: Drawable) {
        background = drawable
        view.background = drawable
    }

    private fun setButtonText(view: AppCompatTextView, text: String, color: Int) {
        view.text = text
        view.run {
            this.text = text
            setTextColor(color)
        }
    }

    fun setButtonBg(bg: Int) {
        this.setBackgroundResource(bg)
    }
}
