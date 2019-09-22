package com.khinthirisoe.lomotif.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.khinthirisoe.lomotif.R
import com.khinthirisoe.lomotif.ui.gallery.GalleryFragment
import com.khinthirisoe.lomotif.ui.video.VideoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setupFragment(supportFragmentManager, viewpager)
        viewpager.currentItem = 0
        viewpager.addOnPageChangeListener(PageChange())
    }

    private fun setupFragment(fragmentManager: FragmentManager, viewPager: ViewPager) {
        val adapter = FragmentPageAdapter(fragmentManager)
        adapter.add(VideoFragment(), "VideoPlayer Page")
        adapter.add(GalleryFragment(), "Gallery Page")
        viewPager.adapter = adapter
    }

    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_video -> {
                        viewpager.currentItem = 0
                        return true
                    }
                    R.id.action_gallery -> {
                        viewpager.currentItem = 1
                        return true
                    }
                }
                return false
            }
        }

    inner class PageChange : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> navigation.selectedItemId = R.id.action_video
                1 -> navigation.selectedItemId = R.id.action_gallery
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}
