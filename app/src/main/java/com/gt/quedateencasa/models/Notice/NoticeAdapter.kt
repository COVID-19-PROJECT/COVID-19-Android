package com.gt.quedateencasa.models.Notice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.gt.quedateencasa.R

class NoticeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>{

    var context:Context?=null
    var items:List<NoticeObject>?=null
    var resource:Int?=null
    constructor(contest:Context, resource:Int, items:List<NoticeObject>){
        this.context = context
        this.resource = resource
        this.items = items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_webview, parent, false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (items == null || items!!.isEmpty()) {
            0
        }
        else items!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val noticeHolder:NoticeViewHolder = holder as NoticeViewHolder;
        val notice:NoticeObject = items!!.get(position)
        val title: String? = notice.Name
        val url: String? = notice.URL

        noticeHolder.title?.setText(title)
        noticeHolder.webView!!.loadUrl("http://www.puedosalirdecasa.es")

        if(title == null || title?.isEmpty()) noticeHolder.title?.visibility = View.GONE;
        noticeHolder.webView!!.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) noticeHolder.progressBar!!.visibility = View.GONE;
            }
        })
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }
}