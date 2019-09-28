package com.khinthirisoe.lomotif.ui.video

import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.khinthirisoe.lomotif.R
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment : Fragment() {

    private var video: ArrayList<String> = arrayListOf()

    private val videoAdapter: VideoAdapter = VideoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_load.setOnClickListener {
            printNamesToLogCat(view.context)
        }
    }

    private fun printNamesToLogCat(context: Context) {
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Video.VideoColumns.DATA)
        val c = context.contentResolver.query(uri, projection, null, null, null)
        var videosCount = 0
        if (c != null) {
            videosCount = c.count
            while (c.moveToNext()) {
                video.add(c.getString(0))
                Log.d("message", c.getString(0))
            }
            c.close()
        }

        Log.d("message",videosCount.toString())

        val gridLayoutManager = LinearLayoutManager(context)

        with(recyclerVideo) {
            layoutManager = gridLayoutManager
        }

        videoAdapter.setData(video)
        recyclerVideo.adapter = videoAdapter

    }

    companion object{
        const val FILE_REQUEST_CODE = 1
    }
}
