package ca.cmpt362.projects.myRun2Tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyRunFragmentStateAdapter(activity: FragmentActivity, var fragmentList: ArrayList<Fragment> ): FragmentStateAdapter(activity) {
    // callback function:
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

}