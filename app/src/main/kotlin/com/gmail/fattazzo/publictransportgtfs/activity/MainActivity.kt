package com.gmail.fattazzo.publictransportgtfs.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.afollestad.materialdialogs.MaterialDialog
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.ParamsFragment_
import com.gmail.fattazzo.publictransportgtfs.fragment.main.MainFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import com.gmail.fattazzo.publictransportgtfs.utils.PermissionsUtil
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
            PermissionsUtil.askPermissions(this)
            FragmentUtils.replace(this, MainFragment_.builder().build())
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_search_feeds -> FragmentUtils.replace(this, ParamsFragment_.builder().build())
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
                val done = fragment.backPressed()
                if (done) {
                    break
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionsUtil.PERMISSION_ALL -> {
                if (grantResults.isNotEmpty()) {
                    val indexesOfPermissionsNeededToShow = permissions.indices
                            .filter { ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[it]) }
                            .toMutableList()

                    val size = indexesOfPermissionsNeededToShow.size
                    if (size != 0) {
                        var i = 0
                        var isPermissionGranted = true

                        while (i < size && isPermissionGranted) {
                            isPermissionGranted = grantResults[indexesOfPermissionsNeededToShow[i]] == PackageManager.PERMISSION_GRANTED
                            i++
                        }

                        if (!isPermissionGranted) {
                            MaterialDialog.Builder(this)
                                    .title(R.string.dialog_permission_mandatory_title)
                                    .content(R.string.dialog_permission_mandatory_content)
                                    .cancelable(false)
                                    .positiveText(android.R.string.ok)
                                    .onPositive { _, _ -> PermissionsUtil.askPermissions(this) }
                                    .build()
                                    .show()
                        }
                    }
                }
            }
        }
    }
}
