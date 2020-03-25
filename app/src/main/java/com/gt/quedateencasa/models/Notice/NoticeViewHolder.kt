package com.gt.quedateencasa.models.Notice

import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_webview.view.*

class NoticeViewHolder:RecyclerView.ViewHolder{
    var title: TextView? = null
    var progressBar:ProgressBar?=null
    var webView:WebView?=null

    constructor(view:View):super(view){
        title = view.title
        progressBar = view.progress_bar
        webView = view.webview
    }
}