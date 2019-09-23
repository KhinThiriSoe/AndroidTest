package com.khinthirisoe.lomotif.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.core.network.ResponseError
import com.khinthirisoe.lomotif.data.DataSourceProperties
import com.khinthirisoe.lomotif.data.gallery.Hits
import com.khinthirisoe.lomotif.ui.Failed
import com.khinthirisoe.lomotif.ui.custom.EndlessRecyclerViewScrollListener
import com.khinthirisoe.lomotif.ui.custom.OverlayView
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class GalleryFragment : Fragment(), OverlayView.OnOverlayButtonClickedListener {

    private val viewModel: GalleryViewModel by sharedViewModel()

    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    private val galleryAdapter: GalleryAdapter = GalleryAdapter(onItemClicked())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        val gridLayoutManager = GridLayoutManager(context, 2)

        with(view.recyclerGallery) {
            layoutManager = gridLayoutManager
            isNestedScrollingEnabled = false
            toggleEmptyView = { isEmpty ->
                visibility = if (isEmpty) {
                    view.overlayView.show(R.string.empty_gallery)
                    View.GONE
                } else {
                    view.overlayView.hide()
                    View.VISIBLE
                }
            }
            this.adapter = galleryAdapter

            scrollListener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    viewModel.fetchData(DataSourceProperties.VALUE_API_KEY, page)
                }
            }
            addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (galleryAdapter.itemCount == 0)
            viewModel.fetchData(DataSourceProperties.VALUE_API_KEY, 1)
        else
            galleryAdapter.notifyDataSetChanged()

        viewModel.states.observe(this, Observer { state ->
            when (state) {
                is GalleryViewModel.GalleryLoaded -> showGalleryItemList(state.gallery.hits)
                is Failed -> {
                    if (state.error is ResponseError.NoInternetConnection) {
                        overlayView.show(R.string.offline, R.drawable.ic_no_internet_connection, R.string.try_again, this)
                    }
                }
            }
        })
    }

    private fun showGalleryItemList(newList: List<Hits>) {
        with(galleryAdapter) {
            setData(newList)
            notifyDataSetChanged()
        }
    }

    override fun onOverlayButtonClick(view: View) {
        viewModel.fetchData(DataSourceProperties.VALUE_API_KEY, 1)
    }

    private fun onItemClicked(): (Hits) -> Unit {
        return { galleryDetail ->
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
        }
    }

}
