package com.khinthirisoe.lomotif.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.khinthirisoe.lomotif.R
import kotlinx.android.synthetic.main.overlay.view.*

class OverlayView : RelativeLayout, View.OnClickListener {

    private var progressBar: ProgressBar? = null
    private var ivOverlay: ImageView? = null
    private var tvOverlay: TextView? = null
    private var btnOverlay: Button? = null

    private var onOverlayButtonClickedListener: OnOverlayButtonClickedListener? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.overlay, this)
        view.setBackgroundResource(android.R.color.white)
        view.visibility = View.GONE
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnOverlay -> {
                onOverlayButtonClickedListener?.onOverlayButtonClick(view)
            }
        }
    }

    fun displayProgressBar() {
        tvOverlay?.visibility = View.GONE
        ivOverlay?.visibility = View.GONE
        btnOverlay?.visibility = View.GONE
        if (progressBar == null) progressBar = stubProgressBar.inflate() as ProgressBar
        progressBar!!.visibility = View.VISIBLE
        visibility = View.VISIBLE
    }

    fun hide() {
        onOverlayButtonClickedListener = null
        visibility = View.GONE
    }

    fun show(@StringRes stringResId: Int,
             @DrawableRes imageResId: Int,
             @StringRes stringResIdBtn: Int,
             onClickListener: OnOverlayButtonClickedListener?
    ) {

        val imageVisible = imageResId != -1
        if (imageVisible) {
            if (ivOverlay == null) ivOverlay = stubIvOverlay.inflate() as ImageView
            with(ivOverlay!!) {
                setImageResource(imageResId)
                visibility = View.VISIBLE
            }
        } else {
            ivOverlay?.visibility = View.GONE
        }

        if (stringResId != -1) {
            if (tvOverlay == null) tvOverlay = stubTvOverlay.inflate() as TextView
            with(tvOverlay!!) {
                setText(stringResId)
                visibility = View.VISIBLE
            }
        } else {
            tvOverlay?.visibility = View.GONE
        }

        if (stringResIdBtn != -1) {
            if (btnOverlay == null) {
                btnOverlay = stubBtnOverlay.inflate() as Button
                btnOverlay!!.setOnClickListener(this)
            }
            onOverlayButtonClickedListener = onClickListener
            with(btnOverlay!!) {
                setText(stringResIdBtn)
                visibility = View.VISIBLE
            }
        } else {
            btnOverlay?.visibility = View.GONE
        }

        progressBar?.visibility = View.GONE

        visibility = View.VISIBLE

    }

    fun show(@StringRes stringResId: Int) {
        show(stringResId, -1, -1, null)
    }

    fun show(@StringRes stringResId: Int, @DrawableRes imageResId: Int) {
        show(stringResId, imageResId, -1, null)
    }

    fun show(@StringRes stringResId: Int, @StringRes stringResIdBtn: Int, onClickListener: OnOverlayButtonClickedListener) {
        show(stringResId, -1, stringResIdBtn, onClickListener)
    }

    interface OnOverlayButtonClickedListener {
        fun onOverlayButtonClick(view: View)
    }

}