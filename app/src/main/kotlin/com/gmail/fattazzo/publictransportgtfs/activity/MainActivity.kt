package com.gmail.fattazzo.publictransportgtfs.activity

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.transitfeed.Config
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.LocationResponse
import com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.ApiRestCLient
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    @Click
    fun floatingActionButtonClicked() {
        ApiRestCLient.locationsService.getLocations(Config.key).enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                    if (response.isSuccessful) {
                        println("posts loaded from API. Locations: " + response.body().result?.locations?.size)
                    } else {
                        val statusCode = response.code()
                        println("Error code " + statusCode)
                }
            }

            override fun onFailure(call: Call<LocationResponse>?, t: Throwable?) {
                println("error loading from API")
            }
        })
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

    companion object {

        private val TAG = MainActivity::class.java.simpleName
    }
}
