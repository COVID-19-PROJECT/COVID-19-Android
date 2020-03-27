package com.gt.quedateencasa.views.main.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HelpPagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = RecommendationsFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}