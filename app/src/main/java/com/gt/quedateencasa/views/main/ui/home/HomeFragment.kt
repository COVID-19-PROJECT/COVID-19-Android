package com.gt.quedateencasa.views.main.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_stat.view.*


class HomeFragment : Fragment() {

    private val homeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRefreshListener()
        loadData()
    }

    //init section
    fun initRefreshListener()
    {
        refresh_home.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                getURL()
            }
        })
    }

    fun loadData()
    {
        configureStats()
        configureWebView()
        refresh_home.setRefreshing(false)
    }

    //Stats section
    fun configureStats(){
        val confirmedStat = stat_confirmed;
        confirmedStat.stat_title.setText("Confirmados")
        confirmedStat.stat_qty.setText("20")
        confirmedStat.stat_qty.setTextColor(Color.RED)
        val confirmedActive = stat_active;
        confirmedActive.stat_title.setText("Activos")
        confirmedActive.stat_qty.setText("20")
        confirmedActive.stat_qty.setTextColor(Color.GREEN)
        val confirmedRecovered = stat_recovered;
        confirmedRecovered.stat_title.setText("Recuperados")
        confirmedRecovered.stat_qty.setText("20")
        confirmedRecovered.stat_qty.setTextColor(Color.rgb(255, 185, 0))
        val confirmedDead = stat_dead;
        confirmedDead.stat_title.setText("Fallecidos")
        confirmedDead.stat_qty.setText("20")
    }
    //Notices section
    fun configureWebView(){
        getURL()
        webview.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) progress_bar?.visibility = View.GONE;
            }
        })
    }

    private fun getURL() {
        homeViewModel.getNotice(context).observe(viewLifecycleOwner, Observer { value ->
            setURL(value)
        })
        refresh_home.isRefreshing = false
    }

    private fun setURL(URL:String?) {
        webview.loadUrl(URL)
    }
}