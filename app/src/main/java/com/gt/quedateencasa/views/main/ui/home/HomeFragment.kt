package com.gt.quedateencasa.views.main.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_stat.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRefreshListener()
        loadData()
    }

    fun initRefreshListener()
    {
        refresh_home.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                loadData()
            }
        })
    }

    fun loadData()
    {
        configureStats()
        loadWebview()
        refresh_home.setRefreshing(false)
    }
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
    fun loadWebview(){
        val webViewUsage = webview_usage
        webViewUsage.loadUrl("http://www.puedosalirdecasa.es")
        manageLoading(webViewUsage, progress_bar_usage)
        val webViewTips = webview_tips
        webViewTips.loadUrl("http://www.puedosalirdecasa.es")
        manageLoading(webViewTips, progress_bar_tips)
    }

    fun manageLoading(webView: WebView, progressBar:ProgressBar)
    {
        webView.setWebChromeClient(object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, progress: Int) {
            if (progress == 100) {
                progressBar.visibility = View.GONE;
            }
        }
    })
    }
}
