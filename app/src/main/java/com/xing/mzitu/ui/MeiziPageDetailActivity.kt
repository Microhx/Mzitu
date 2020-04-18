package com.xing.mzitu.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xing.mzitu.R
import com.xing.mzitu.adapter.MeiziPreviewListAdapter
import com.xing.mzitu.entity.MeiziDetailItem
import com.xing.mzitu.vm.MeiziPageDetailViewModel
import kotlinx.android.synthetic.main.activity_meizi_page_detail.*

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/15
 *
 * version :
 *
 * desc : 妹子图片浏览
 */
class MeiziPageDetailActivity : AppCompatActivity() {

    private var mRelativeUrl:String?= null


    private lateinit var mMeiziPreviewListAdapter : MeiziPreviewListAdapter

    private lateinit var mMeiziPageDetailViewModel : MeiziPageDetailViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveIntent()
        setContentView(R.layout.activity_meizi_page_detail)

        initRecyclerView()

        initViewModel()
    }

    private fun initRecyclerView() {
        mMeiziPreviewListAdapter = MeiziPreviewListAdapter()
        id_recycler_image.apply {
            adapter = mMeiziPreviewListAdapter
            layoutManager = LinearLayoutManager(this@MeiziPageDetailActivity,LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun receiveIntent() {
        intent?.apply {
            mRelativeUrl = getStringExtra("relativeUrl")
        }
    }

    private fun initViewModel() {
        mMeiziPageDetailViewModel = ViewModelProvider(this).get(MeiziPageDetailViewModel::class.java)

        //数据加载
        mMeiziPageDetailViewModel.loadingTip.observe(this, Observer<String> { t -> id_tv_loading_info.text = t })
        //所有图片预览
        mMeiziPageDetailViewModel.loadingDetailList.observe(this, Observer<List<MeiziDetailItem>> { t -> mMeiziPreviewListAdapter.replaceData(t!!) })

        mMeiziPageDetailViewModel.fetch(mRelativeUrl ?: "")
    }

    companion object {

        fun start(context:Context?, relativeUrl:String){
            context?.startActivity(
                Intent(context,MeiziPageDetailActivity::class.java).
                putExtra("relativeUrl", relativeUrl))
        }


    }


}