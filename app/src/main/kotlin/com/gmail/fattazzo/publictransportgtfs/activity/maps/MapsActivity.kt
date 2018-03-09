package com.gmail.fattazzo.publictransportgtfs.activity.maps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.BaseMapFargment
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.FragmentMapsSelector
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.GoogleMapFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.EActivity

/**
 * @author fattazzo
 *         <p/>
 *         date: 02/03/18
 */
@EActivity(R.layout.activity_maps)
open class MapsActivity : AppCompatActivity() {

    lateinit var fragment: BaseMapFargment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentMap = supportFragmentManager.findFragmentByTag(GoogleMapFragment_::class.java.simpleName) as BaseMapFargment?

        if (fragmentMap == null) {
            fragment = FragmentMapsSelector.getFragment(1)

            if (savedInstanceState == null) {
                FragmentUtils.add(this@MapsActivity, fragment, containerResId = R.id.mapContainer)
            }
        } else {
            fragment = fragmentMap
        }
    }
}