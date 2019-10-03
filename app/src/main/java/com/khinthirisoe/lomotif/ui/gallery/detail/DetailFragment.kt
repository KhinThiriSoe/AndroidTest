package com.khinthirisoe.lomotif.ui.gallery.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.gallery.Hits
import com.khinthirisoe.lomotif.ui.gallery.ForegroundService
import com.khinthirisoe.lomotif.ui.main.MainActivity
import com.khinthirisoe.lomotif.ui.widget.RoundedBottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : RoundedBottomSheetDialogFragment() {

    companion object {

        const val EXTRA_DATA = "data"

        fun newInstance(hits: Hits) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_DATA, hits)
            }
        }
    }

    private var hits : Hits? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hits = arguments!!.getParcelable(EXTRA_DATA)
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

        tvDownload.setOnClickListener {
            startService()
        }
    }

    private fun startService() {

        Intent(context, ForegroundService::class.java)
            .apply {
                this.putExtra(EXTRA_DATA, "Foreground Service Example in Android")
                ContextCompat.startForegroundService(context as MainActivity, this)
            }
    }
}
