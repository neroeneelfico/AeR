package com.example.aer_anestesiaerianimazione

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView
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
import com.ramotion.fluidslider.FluidSlider
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewPager: ViewPager
    var compensiadapter = MyViewPagerAdapter(supportFragmentManager)
    lateinit var tabLayout : TabLayout
    lateinit var phadapter : MyViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottonnavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        phadapter = MyViewPagerAdapter(supportFragmentManager)
        val hco3adapter = MyViewPagerAdapter(supportFragmentManager)


        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        val max = 45
        val min = 10
        val total = max - min



        phadapter.addFragment(FragmentInserimento(),"Calcolo")
        phadapter.addFragment(FragmentCompensi(),"Compensi")

        compensiadapter.addFragment(FragmentInserimento(),"Calcolo")

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

        fun addFragmentBundle(fragment: Fragment, title:String, co2:Float, hco3:Float){
            val bundle = Bundle()
            bundle.putFloat("co2",co2)
            bundle.putFloat("hco3",hco3)
            fragment.arguments = bundle
            fragmentList[1] = fragment
            //fragmentList.add(fragment)
           // titleList.add(title)
            Log.i("MainActivity","Arrivato")

        }

        fun resetFragment(){
            fragmentList.clear()
            fragmentList.removeAt(0)
            titleList.clear()
            titleList.removeAt(0)
        }
        fun aggiornaCompensi(tipo : String, co2:Float){
            (fragmentList[1] as FragmentCompensi).aggiornaCompensi(tipo, co2)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }



}