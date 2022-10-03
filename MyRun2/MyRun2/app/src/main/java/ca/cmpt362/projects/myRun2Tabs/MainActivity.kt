//Sept27,2022 My Run 2
//Reference:
//https://stackoverflow.com/questions/1957831/center-a-button-in-a-linear-layout
//https://www.digitalocean.com/community/tutorials/android-alert-dialog-using-kotlin#9-alert-dialog-with-edit-text
//https://www.youtube.com/watch?v=J5ZdR-vWlaw&t=800s&ab_channel=Howcang
//https://devofandroid.blogspot.com/2018/03/how-to-create-singlechoice-alertdialog.html
//https://stackoverflow.com/questions/33925206/how-do-you-open-a-web-browser-using-links-in-a-listview-via-android-studio

package ca.cmpt362.projects.myRun2Tabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    //1.
    private lateinit var fragmentStart: FragmentStart
    private lateinit var fragmentHistory: FragmentHistory
    private lateinit var fragmentSettings: FragmentSettings
    private lateinit var fragments: ArrayList<Fragment>

    //3.
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2 //container for all fragments,

    //4.
    lateinit var myRunFragmentStateAdapter: MyRunFragmentStateAdapter

    //6.for displaying Tabs:
    private val tabTitles = arrayOf("START", "HISTORY", "SETTINGS")
    private lateinit var tabConfigurationStrategy: TabLayoutMediator.TabConfigurationStrategy //the strategy tells Android that there's a array "tabTitles"
    private lateinit var tabLayoutMediator: TabLayoutMediator //mediator connects tabs and viewpager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragmentsTabs()  //create and contain 3 fragments, 3 tabs for swiping

    }

    private fun setupFragmentsTabs(){
        //2.
//        if(supportFragmentManager.findFragmentByTag("tag") == null) {
            fragmentStart = FragmentStart()
            fragmentHistory = FragmentHistory()
            fragmentSettings = FragmentSettings()


//                supportFragmentManager.beginTransaction().add(android.R.id.content, fragmentStart).commit()
//                supportFragmentManager.beginTransaction().add(android.R.id.content, fragmentHistory).commit()
//                supportFragmentManager.beginTransaction().add(android.R.id.content, fragmentSettings).commit()

//        }

        fragments = ArrayList()

        fragments.add(fragmentStart)
        fragments.add(fragmentHistory)
        fragments.add(fragmentSettings)

        //3.
        tabLayout = findViewById(R.id.tab)
        viewPager2 = findViewById(R.id.viewpager)

        //4.
        myRunFragmentStateAdapter = MyRunFragmentStateAdapter(this, fragments)

        //5. Display the tabs:
        viewPager2.adapter = myRunFragmentStateAdapter

        tabConfigurationStrategy = TabLayoutMediator.TabConfigurationStrategy(){
                tab: TabLayout.Tab, position: Int -> tab.text = tabTitles[position]
        }
        tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager2, tabConfigurationStrategy)
        tabLayoutMediator.attach()


    }


    override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator.detach() // to free some memory when the app is killed
    }

}