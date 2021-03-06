package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland

import android.widget.FrameLayout
import android.widget.Spinner
import android.widget.TextView
import com.gmail.fattazzo.feedsources.transitland.LocationsManager
import com.gmail.fattazzo.feedsources.transitland.domain.Location
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.about.AboutFragment_
import com.gmail.fattazzo.publictransportgtfs.fragment.main.MainFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import com.hbb20.CountryCodePicker
import org.androidannotations.annotations.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 05/02/18
 */
@EFragment(R.layout.fragment_search_operators_params)
open class ParamsFragment : BaseFragment(), CountryCodePicker.OnCountryChangeListener {

    @JvmField
    @InstanceState
    var searchParams: SearchParams = SearchParams()

    @ViewById
    lateinit var countrySpinner: CountryCodePicker

    @ViewById
    lateinit var locationSpinner: Spinner

    @JvmField
    @ViewById
    var nameTV: TextView? = null

    @JvmField
    @ViewById
    var searchContainer: FrameLayout? = null

    @Bean
    lateinit var locationsManager: LocationsManager

    @AfterViews
    fun init() {
        bindViews()
    }

    private fun bindViews() {
        if (searchParams.countryCode.isBlank()) {
            searchParams.countryCode = countrySpinner.selectedCountryNameCode
        } else {
            countrySpinner.setCountryForNameCode(searchParams.countryCode)
        }
        countrySpinner.setOnCountryChangeListener(this)

        populateLocations()

        nameTV?.text = searchParams.name.orEmpty()
    }

    private fun populateLocations() {
        val locations = locationsManager.getLocations(searchParams.countryCode)
        val spinAdapter = LocationsAdapter(locations, context!!)
        locationSpinner.adapter = spinAdapter
        if (searchParams.location != null && locations.contains(searchParams.location!!)) {
            locationSpinner.setSelection(locations.indexOf(searchParams.location!!))
        }
    }

    @ItemSelect
    fun locationSpinnerItemSelected(selected: Boolean, location: Location) {
        searchParams.location = location
    }

    @FocusChange
    fun nameTVFocusChanged() {
        searchParams.name = nameTV?.text?.toString()
    }

    @Click
    fun clearFABClicked() {
        searchParams = SearchParams()
        bindViews()
    }

    @Click
    fun searchFABClicked() {
        if (searchContainer == null) {
            FragmentUtils.add(activity, ResultsFragment_.builder().searchParams(searchParams).build(),
                    animationType = FragmentUtils.AnimationType.RIGHT_TO_LEFT)
        } else {
            FragmentUtils.replace(activity, ResultsFragment_.builder().backpressHandling(false).searchParams(searchParams).build(),
                    animationType = FragmentUtils.AnimationType.FADE_IN, containerResId = R.id.searchContainer)
        }
    }

    override fun onCountrySelected() {
        searchParams.countryCode = countrySpinner.selectedCountryNameCode
        populateLocations()
    }

    @Click
    fun goAboutTVClicked() {
        FragmentUtils.add(activity, AboutFragment_.builder().build(), animationType = FragmentUtils.AnimationType.FADE_IN)
    }

    override fun backPressed(): Boolean {
        FragmentUtils.replace(activity, MainFragment_.builder().build())
        return true
    }
}