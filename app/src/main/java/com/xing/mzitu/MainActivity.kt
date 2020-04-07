package com.xing.mzitu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xing.mzitu.adapter.MainItemAdapter
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.fragment.MainPageFragment
import com.xing.mzitu.vm.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment()

    }

    private fun addFragment() {
        Log.d("TAG","add fragment")

        supportFragmentManager.beginTransaction().
            add(R.id.id_content_layout, MainPageFragment.getInstance("")).
            commitAllowingStateLoss()


    }


}

