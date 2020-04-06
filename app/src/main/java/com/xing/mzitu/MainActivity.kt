package com.xing.mzitu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xing.mzitu.adapter.MainItemAdapter
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.vm.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDataViewModel: DataViewModel

    private lateinit var mMainItemAdapter: MainItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        mDataViewModel = ViewModelProvider(this@MainActivity).get(DataViewModel::class.java)
        initObserver()
    }

    private fun initRecyclerView() {
        mMainItemAdapter = MainItemAdapter()

        id_main_recycler_view.apply {
            adapter = mMainItemAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObserver() {
        mDataViewModel.meituList.observe(this, Observer<List<MeiziItem>> {
            mMainItemAdapter.update(it)
        })
    }

}

