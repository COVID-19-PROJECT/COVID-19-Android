package com.gt.quedateencasa.views.main.ui.home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gt.quedateencasa.R
import com.gt.quedateencasa.listeners.ScrollListener
import com.gt.quedateencasa.models.Notice.NoticeObject
import com.gt.quedateencasa.models.Notice.NoticeAdapter
import com.gt.quedateencasa.models.Notice.NoticeService
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_stat.view.*
import java.util.*


class HomeFragment : Fragment() {

    private val homeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private val noticeAdapter by lazy {
        NoticeAdapter()
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
        getNotices()
    }

    //init section
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
        loadWebviews()
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
    fun loadWebviews(){
        configureRecyclerView()
    }
    fun configureRecyclerView(){
        recycler_view_news.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view_news.adapter = noticeAdapter
        recycler_view_news.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        recycler_view_news.itemAnimator = DefaultItemAnimator()
        recycler_view_news.addOnScrollListener(object : ScrollListener() {
            override fun onScrollUp() {}
            override fun onScrollDown() {}
            override fun onLoadMore() {
                getNotices()
            }
        })
    }

    fun getNotices() {
        homeViewModel.getNotices(context).observe(viewLifecycleOwner, Observer {
            setNoticesInList(it)
        })
    }
    fun setNoticesInList(list: List<NoticeObject>) {
        noticeAdapter.updateItems(list)
        refresh_home.setRefreshing(false)
    }
}
