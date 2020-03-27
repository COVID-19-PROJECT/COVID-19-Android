package com.gt.quedateencasa.views.main.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.fragment_web_views.*

/**
 * A simple [Fragment] subclass.
 */
class WebViewsFragment : Fragment() {


    companion object {
        private const val ARG_OBJECT = "object"
    }

    private val recomendationViewModel by lazy {
        ViewModelProviders.of(this).get(WebViewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_views, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            refresh_section.setOnRefreshListener { getURL(getInt(ARG_OBJECT)) }
            getURL(getInt(ARG_OBJECT))
            configureWebView()
        }
    }

    private fun configureWebView(){
        refresh_section.isRefreshing = false
        webview_section.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) progress_bar_section.visibility = View.GONE
            }
        }
    }

    private fun getURL(position:Int) {
        recomendationViewModel.getRecomendation(position, context).observe(viewLifecycleOwner, Observer { value ->
            setURL(value)
        })
        refresh_section.isRefreshing = false
    }

    private fun setURL(URL:String?) {
        webview_section.loadUrl(URL)
    }
}
