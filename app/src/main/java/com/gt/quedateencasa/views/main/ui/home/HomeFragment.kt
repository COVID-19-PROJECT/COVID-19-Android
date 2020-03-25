package com.gt.quedateencasa.views.main.ui.home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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

    private lateinit var noticeService:NoticeService
    private lateinit var itemAdapter:NoticeAdapter
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
        noticeService = NoticeService()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRefreshListener()
        loadData()
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
        configureRecyclerView(noticeService.findNotices())
    }
    fun configureRecyclerView(items:List<NoticeObject>){
        itemAdapter = NoticeAdapter(context!!, R.layout.item_webview, items = items)
        recycler_view_news.setLayoutManager(LinearLayoutManager(getContext()));
        recycler_view_news.setAdapter(itemAdapter)

        recycler_view_news.addOnScrollListener(object : ScrollListener() {
            override fun onScrollUp() {}
            override fun onScrollDown() {}
            override fun onLoadMore() {
                getNotices()
            }
        })
    }

    fun getNotices(){
        val items: ArrayList<NoticeObject> = noticeService.findNotices()
        val handler = Handler()
        val r = Runnable {
            items.clear()
            items.addAll(items)
            itemAdapter.notifyDataSetChanged()
        }
        handler.postDelayed(r, 1600)
        refresh_home.setRefreshing(false)
    }
}
