package com.khinthirisoe.lomotif

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentPageAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragment = arrayListOf<Fragment>()
    private val pageName = arrayListOf<String>()

    fun add(frag: Fragment, title: String) {
        fragment.add(frag)
        pageName.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageName[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }
}
