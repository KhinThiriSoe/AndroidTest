package com.khinthirisoe.lomotif.ui.gallery.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.gallery.Hits
import com.khinthirisoe.lomotif.ui.custom.RoundedBottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : RoundedBottomSheetDialogFragment() {

    private var hits : Hits? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hits = arguments!!.getParcelable<Hits>("data")

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hits?.let {
            Glide.with(view.context)
                .load(it.webformatURL)
                .override(it.webformatWidth, it.webformatWidth)
                .into(imageView)

            tvLikes.text = it.likes.toString()
            tvComments.text = it.comments.toString()
        }

    }

}
