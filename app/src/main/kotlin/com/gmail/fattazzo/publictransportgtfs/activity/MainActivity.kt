package com.gmail.fattazzo.publictransportgtfs.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.main.MainFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById


@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    internal lateinit var navView: NavigationView

    @ViewById
    internal lateinit var drawerLayout: DrawerLayout

    @ViewById
    internal lateinit var toolbar: Toolbar

    @AfterViews
    protected fun init() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.itemIconTintList = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // Instanzio il fragment se savedInstanceState == null altrimenti (ad es. girando il dispositivo)
            // viene rimesso anche se quello attivo è un altro
            FragmentUtils.replace(this, MainFragment_.builder().build())
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
        /**
        R.id.nav_current_season_driver -> FragmentUtils.replace(this, CurrentDriversFragment_.builder().build())
        R.id.nav_current_season_constructor -> FragmentUtils.replace(this, CurrentConstructorsFragment_.builder().build())
        R.id.nav_current_season_race -> FragmentUtils.replace(this, CurrentRacesFragment_.builder().build())
        R.id.nav_news -> FragmentUtils.replace(this, NewsFragment_.builder().build())
        R.id.nav_collaborate -> FragmentUtils.replace(this, CollaborateFragment_.builder().build())
        R.id.nav_stats_drivers -> FragmentUtils.replace(this, StatisticsDriversFragment_.builder().build())
        R.id.nav_stats_constructors -> FragmentUtils.replace(this, StatisticsConstructorsFragment_.builder().build())
        R.id.nav_stats_season -> FragmentUtils.replace(this, StatisticsSeasonFragment_.builder().build())
        R.id.nav_about -> AboutActivity_.intent(this).start()
         **/
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        val fragmentManager = supportFragmentManager

        val fragments = fragmentManager.fragments

        val li = fragments.listIterator(fragments.size)
        while (li.hasPrevious()) {
            val fragment = li.previous() as Fragment
            if (fragment is BaseFragment) {
                val done = (fragment as BaseFragment).backPressed()
                if (done) {
                    break
                }
            }
        }
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName
    }
}
