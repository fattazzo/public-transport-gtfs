package com.gmail.fattazzo.publictransportgtfs.fragment.main

import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.transitfeed.locations.LocationsFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EFragment(R.layout.fragment_main)
open class MainFragment : BaseFragment() {

    @Click
    fun buttonClicked() {
        FragmentUtils.replace(activity, LocationsFragment_.builder().build())
    }
}