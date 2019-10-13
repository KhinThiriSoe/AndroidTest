package com.khinthirisoe.lomotif.ui.gallery.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.core.network.ResponseError
import com.khinthirisoe.lomotif.data.gallery.Hits
import com.khinthirisoe.lomotif.ui.Failed
import com.khinthirisoe.lomotif.ui.Loading
import com.khinthirisoe.lomotif.ui.gallery.detail.DetailFragment
import com.khinthirisoe.lomotif.ui.widget.EndlessRecyclerViewScrollListener
import com.khinthirisoe.lomotif.ui.widget.OverlayView
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class GalleryFragment : Fragment(), OverlayView.OnOverlayButtonClickedListener {

    private val viewModel: GalleryViewModel by viewModel()

    private var hitList: ArrayList<Hits> = arrayListOf()

    private val galleryAdapter: GalleryAdapter = GalleryAdapter(::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)


        view.galleryRecyclerView.apply {
            val gridLayoutManager = GridLayoutManager(context, 2)
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

            addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager)  {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    viewModel.fetchData(page)
                }
            })
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (galleryAdapter.itemCount == 0)
            viewModel.fetchData(1)
        else
            galleryAdapter.notifyDataSetChanged()

        viewModel.states.observe(this, Observer { state ->
            when (state) {
                is Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is GalleryViewModel.GalleryLoaded -> {
                    progressBar.visibility = View.GONE
                    showGalleryItemList(state.gallery.hits)
                }
                is Failed -> {
                    progressBar.visibility = View.GONE
                    if (state.error is ResponseError.NoInternetConnection) {
                        overlayView.show(R.string.offline, R.drawable.ic_no_internet_connection, R.string.try_again, this)
                    }
                }
            }
        })
    }

    private fun showGalleryItemList(newList: List<Hits>) {
        this.hitList.addAll(newList)
        galleryAdapter.hitList = hitList
    }

    override fun onOverlayButtonClicked(view: View) {
        viewModel.fetchData(1)
    }

    private fun onItemClicked(hits: Hits) {
        DetailFragment.newInstance(hits)
            .show(childFragmentManager, DetailFragment::class.java.simpleName)
    }

}
