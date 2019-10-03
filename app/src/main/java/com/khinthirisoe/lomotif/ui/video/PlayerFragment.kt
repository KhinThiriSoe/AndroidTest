package com.khinthirisoe.lomotif.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.ui.widget.RoundedBottomSheetDialogFragment

class PlayerFragment : RoundedBottomSheetDialogFragment() {

    private var path:String? = null

    companion object {

        private const val EXTRA_DATA = "data"

        fun newInstance(path: String) = PlayerFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_DATA, path)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        path = arguments!!.getString(EXTRA_DATA)

        Log.d("message","path " + path)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
