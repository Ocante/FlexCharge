package com.bless.flexcharge

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bless.flexcharge.electric.ElectricFragment
import com.bless.flexcharge.fuel.FuelFragment

class FuelPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FuelFragment()
            else -> ElectricFragment()
        }
    }
}
