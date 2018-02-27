package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland

import android.widget.Spinner
import android.widget.TextView
import com.gmail.fattazzo.feedsources.transitland.LocationsManager
import com.gmail.fattazzo.feedsources.transitland.domain.Location
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
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

    @ViewById
    lateinit var nameTV: TextView

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

        nameTV.text = searchParams.name.orEmpty()
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
        searchParams.name = nameTV.text.toString()
    }

    @Click
    fun clearFABClicked() {
        searchParams = SearchParams()
        bindViews()
    }

    @Click
    fun searchFABClicked() {
        FragmentUtils.add(activity, ResultsFragment_.builder().searchParams(searchParams).build(),
                animationType = FragmentUtils.AnimationType.RIGHT_TO_LEFT)
    }

    override fun onCountrySelected() {
        searchParams.countryCode = countrySpinner.selectedCountryNameCode
        populateLocations()
    }

    override fun backPressed(): Boolean {
        activity?.supportFragmentManager?.popBackStack()
        return true
    }
}