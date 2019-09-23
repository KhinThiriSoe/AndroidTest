package com.khinthirisoe.lomotif.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.data.DataSourceProperties
import com.khinthirisoe.lomotif.data.gallery.Hits
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by sharedViewModel()

    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private var list: ArrayList<Hits> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareListView()

        viewModel.fetchData(DataSourceProperties.VALUE_API_KEY, 1)

        viewModel.states.observe(this, Observer { state ->
            when (state) {
                is GalleryViewModel.GalleryLoaded -> {
                    showGalleryItemList(state.gallery.hits)
                }
            }
        })
    }

    private fun prepareListView() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerGallery.adapter = GalleryAdapter(emptyList())
        recyclerGallery.layoutManager = layoutManager
        recyclerGallery.isNestedScrollingEnabled = false

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.fetchData(DataSourceProperties.VALUE_API_KEY, page)
            }
        }
        recyclerGallery.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
    }

    private fun showGalleryItemList(newList: List<Hits>) {
        val adapter = recyclerGallery.adapter as GalleryAdapter
        list.addAll(newList)
        adapter.list = list
        adapter.notifyDataSetChanged()
    }
}
