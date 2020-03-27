package com.gt.quedateencasa.views.main.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator

import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.fragment_help.*

class HelpFragment : Fragment() {

    private lateinit var adapter: HelpPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HelpPagerAdapter(this)
        pager.adapter = adapter

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_recomendations)
                1 -> tab.text = getString(R.string.title_communications)
            }
        }.attach()
    }
}
