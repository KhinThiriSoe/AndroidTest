package com.khinthirisoe.lomotif.ui.gallery.detail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.bumptech.glide.Glide
import com.esafirm.rxdownloader.RxDownloader
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.gallery.Hits
import com.khinthirisoe.lomotif.ui.main.MainActivity
import com.khinthirisoe.lomotif.ui.widget.RoundedBottomSheetDialogFragment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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

        hits = arguments?.getParcelable(EXTRA_DATA)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        checkPermission()

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
            startSample()
        }
    }

    private fun checkPermission() {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        val permissionCheck = ContextCompat.checkSelfPermission(context as MainActivity, permission)

        if (permissionCheck != PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as MainActivity, arrayOf(permission), 0)
            return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        if (requestCode != 0) return
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startSample()
        }
    }

    private fun startSample() {
        val rxDownloader = RxDownloader(context as MainActivity)
        rxDownloader.download(
            hits?.webformatURL!!,
            hits?.user!!,
            "image/jpg",
            true)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    d.dispose()
                }

                override fun onNext(filePath: String) {
                    Toast.makeText(context, "Downloaded to $filePath", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                }

                override fun onComplete() {
                    Log.d(
                        "Sample",
                        "Is in main thread? " + (Looper.getMainLooper() == Looper.myLooper())
                    )
                }
            })
    }
}
