package com.gt.quedateencasa.listeners

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//Thanks to https://github.com/jayrambhia/FooterLoaderAdapterDemo
abstract class ScrollListener : RecyclerView.OnScrollListener() {
    private var mScrollThreshold = 40
    private var scrolledDistance = 0
    private var isLoading =
        true // True if we are still waiting for the last set of data to load.
    private var previousTotal =
        0 // The total number of items in the dataset after the last load
    private val visibleThreshold =
        1 // The minimum amount of items to have below your current scroll position before isLoading more.
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    private var infiniteScrollingEnabled = true
    private var controlsVisible = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val manager: RecyclerView.LayoutManager? = recyclerView.getLayoutManager()
        visibleItemCount = recyclerView.getChildCount()
        if (manager is GridLayoutManager) {
            val gridLayoutManager: GridLayoutManager = manager as GridLayoutManager
            firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
            totalItemCount = gridLayoutManager.getItemCount()
        } else if (manager is LinearLayoutManager) {
            val linearLayoutManager: LinearLayoutManager = manager as LinearLayoutManager
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
            totalItemCount = linearLayoutManager.getItemCount()
        }
        if (infiniteScrollingEnabled) {
            if (isLoading) {
                if (totalItemCount > previousTotal) {
                    isLoading = false
                    previousTotal = totalItemCount
                }
            }
            if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) { // End has been reached
// do something
                onLoadMore()
                isLoading = true
            }
        }
        if (firstVisibleItem == 0) {
            if (!controlsVisible) {
                onScrollUp()
                controlsVisible = true
            }
            return
        }
        if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
            onScrollDown()
            controlsVisible = false
            scrolledDistance = 0
        } else if (scrolledDistance < - HIDE_THRESHOLD && !controlsVisible) {
            onScrollUp()
            controlsVisible = true
            scrolledDistance = 0
        }
        if (controlsVisible && dy > 0 || !controlsVisible && dy < 0) {
            scrolledDistance += dy
        }
    }

    abstract fun onScrollUp()
    abstract fun onScrollDown()
    abstract fun onLoadMore()
    fun setScrollThreshold(scrollThreshold: Int) {
        mScrollThreshold = scrollThreshold
    }

    fun stopInfiniteScrolling() {
        infiniteScrollingEnabled = false
    }

    fun onDataCleared() {
        previousTotal = 0
    }

    companion object {
        private const val TAG = "ScrollListener"
        private const val HIDE_THRESHOLD = 20
    }
}