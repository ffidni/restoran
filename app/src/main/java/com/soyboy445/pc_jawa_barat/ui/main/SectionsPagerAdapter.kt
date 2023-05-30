package com.soyboy445.pc_jawa_barat.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.soyboy445.pc_jawa_barat.R
import com.soyboy445.pc_jawa_barat.ui.main.tabs.Cart
import com.soyboy445.pc_jawa_barat.ui.main.tabs.Menu
import com.soyboy445.pc_jawa_barat.ui.main.tabs.Orders

private val TAB_TITLES = arrayOf(
    "Menu",
    "Cart",
    "Orders"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> {return Menu()}
            1 -> {return Cart()}
            2 -> {return Orders()}
            else -> {return getItem(position)}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }
}