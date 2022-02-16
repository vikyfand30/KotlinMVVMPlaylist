package com.example.femaledailytest.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.femaledailytest.FavoriteFragment
import com.example.femaledailytest.PlaylistFragment

internal class FragmentAdapter (
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
)    :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PlaylistFragment()
            1 -> FavoriteFragment()
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}