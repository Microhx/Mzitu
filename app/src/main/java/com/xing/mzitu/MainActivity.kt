package com.xing.mzitu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xing.mzitu.fragment.AbstractTabFragment
import com.xing.mzitu.fragment.CategoryTabFragment
import com.xing.mzitu.fragment.HomeTabFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment()
    }

    private fun addFragment() {
        Log.d("TAG","add fragment")

        supportFragmentManager.beginTransaction().
            add(R.id.id_content_layout, CategoryTabFragment()).
            commitAllowingStateLoss()
    }


}

