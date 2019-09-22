package com.khinthirisoe.lomotif.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.DataSourceProperties
import org.koin.android.viewmodel.ext.android.sharedViewModel

class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchData(DataSourceProperties.VALUE_API_KEY, 1)

        viewModel.states.observe(this, Observer { state ->
            when (state) {
                is GalleryViewModel.GalleryLoaded ->
                    Toast.makeText(context, state.gallery.size.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}
