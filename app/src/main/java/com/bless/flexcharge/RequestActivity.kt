package com.bless.flexcharge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        val pager = findViewById<ViewPager2>(R.id.view_pager)
        val tabs = findViewById<TabLayout>(R.id.tab_layout)

        val adapter = RequestPagerAdapter(this)
        pager.adapter = adapter

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = if (position == 0) "Combustível" else "Elétrico"
        }.attach()
    }
}
