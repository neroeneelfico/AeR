package com.example.aer_anestesiaerianimazione

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottonnavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val phadapter = MyViewPagerAdapter(supportFragmentManager)
        val hco3adapter = MyViewPagerAdapter(supportFragmentManager)
        phadapter.addFragment(FragmentInserimento(),"Inserimento")
        phadapter.addFragment(FragmentInserimento(),"Risultato")
        phadapter.addFragment(FragmentInserimento(),"Dettagli")

        hco3adapter.addFragment(FragmentInserimento(),"Calcolo hco3")
        hco3adapter.addFragment(FragmentInserimento(),"Dettagli")


        viewPager.adapter = phadapter
        setSupportActionBar(toolbar)
        tabLayout.setupWithViewPager(viewPager)


        bottonnavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.page_1 -> {
                    tabLayout.removeAllTabs()
                    viewPager.adapter = phadapter
                    tabLayout.setupWithViewPager(viewPager)

                }
                R.id.page_2 -> {
                    tabLayout.removeAllTabs()
                    viewPager.adapter = hco3adapter
                    tabLayout.setupWithViewPager(viewPager)
                }
            }
            true }

    }

    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title:String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        fun resetFragment(){
            fragmentList.clear()
            fragmentList.removeAt(0)
            titleList.clear()
            titleList.removeAt(0)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }


}