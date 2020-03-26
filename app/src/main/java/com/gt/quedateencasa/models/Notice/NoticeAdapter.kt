package com.gt.quedateencasa.models.Notice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.item_webview.view.*

class NoticeAdapter: RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>(){

    private val items = arrayListOf<NoticeObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeAdapter.NoticeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_webview, parent, false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class NoticeViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: NoticeObject, position: Int) {
            v.title?.setText(item.Name)
            v.webview.loadUrl(item.URL)

            if(item.Name == null) v.title?.visibility = View.GONE;
            v.webview!!.setWebChromeClient(object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, progress: Int) {
                    if (progress == 100) v.progress_bar!!.visibility = View.GONE;
                }
            })
        }
    }

    fun updateItems(list: List<NoticeObject>) {
        items?.clear()
        items?.addAll(list)
        notifyDataSetChanged()
    }
}