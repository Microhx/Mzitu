package com.xing.mzitu.base

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xing.mzitu.R
import com.xing.mzitu.adapter.BaseCommonAdapter
import com.xing.mzitu.entity.ResponseLogicData
import com.xing.mzitu.utils.BaseCommonUtils
import com.xing.mzitu.utils.UIUtils
import com.xing.mzitu.view.DefaultLoadMoreView
import kotlinx.android.synthetic.main.fragment_base_refresh_layout.*

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/6
 *
 * version :
 *
 * desc :
 */
abstract class BaseRefreshFragment<T> : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    protected lateinit var recyclerViewAdapter : BaseCommonAdapter<T>

    private var mCurrentPage = FIRST_PAGE

    override fun getResLayoutId(): Int = R.layout.fragment_base_refresh_layout

    override fun afterViewCreated() {
        initRefreshLayout()
        initRecyclerView()
    }

    private fun initRefreshLayout() {
        id_swipe_refresh_layout.apply {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary))
            setProgressViewEndTarget(false, UIUtils.dp2px(80))

            if(refreshLayoutEnabled()){
                setOnRefreshListener(this@BaseRefreshFragment)
            }else{
                isEnabled = false
            }
        }
    }

    private fun initRecyclerView() {
        id_refresh_recycler_view.apply {
            initLayoutManager(this)
            initAdapter(this)
            initItemDecoration(this)
        }
    }

    private fun initAdapter(recyclerView: RecyclerView) {
        recyclerViewAdapter = subClassInitAdapter()
        recyclerViewAdapter.apply {
            setOnItemClickListener { _, _, position ->
                onItemChildClick(this, this.getItem(position), position)
            }

            setLoadMoreView(DefaultLoadMoreView())

            setOnLoadMoreListener({
                loadMoreData()
            },id_refresh_recycler_view)
        }

        recyclerView.adapter = recyclerViewAdapter
    }

    open fun initLayoutManager(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    open fun refreshLayoutEnabled(): Boolean = true

    open fun onItemChildClick(adapter: BaseCommonAdapter<T>, item: T?, position: Int) {}

    open fun initItemDecoration(recyclerView: RecyclerView) {}

    override fun onRefresh() {
        mCurrentPage = FIRST_PAGE
        requestLoadData(mCurrentPage)
    }

    open fun loadMoreData() {
        requestLoadData(mCurrentPage)
    }

    abstract fun subClassInitAdapter(): BaseCommonAdapter<T>

    abstract fun requestLoadData(currentPage:Int)

    protected fun parseData(logicData: ResponseLogicData<T>) {
        id_swipe_refresh_layout.isRefreshing = false
        val safeDataList = BaseCommonUtils.getSafeArrayList(logicData.dataList)
        
        mCurrentPage ++
        if(logicData.pageIndex < FIRST_PAGE){
            recyclerViewAdapter.replaceData(safeDataList)
        }else{
            recyclerViewAdapter.addData(safeDataList)
        }

        recyclerViewAdapter.loadMoreComplete()

        if(safeDataList.size < ITEM_PAGE_SIZE){
            recyclerViewAdapter.setEnableLoadMore(false)
        }else{
            recyclerViewAdapter.setEnableLoadMore(true)
        }
    }

    companion object {
        const val FIRST_PAGE = 1

        const val ITEM_PAGE_SIZE = 12
    }

}