package com.gmail.fattazzo.publictransportgtfs.fragment.transitfeed.locations

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.RecyclerViewAdapterBase
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.main.MainFragment_
import com.gmail.fattazzo.publictransportgtfs.fragment.transitfeed.feeds.FeedsFragment_
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.Location
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.response.LocationResponse
import com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.ApiRestCLient
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EFragment(R.layout.fragment_locations)
open class LocationsFragment : BaseFragment() {

    var locationsList: LinkedList<MutableList<Location>> = LinkedList()

    @Bean
    lateinit var locationAdapter: LocationAdapter

    @ViewById
    lateinit var locationRecyclerView: RecyclerView

    @ViewById
    lateinit var titleTV: TextView

    @AfterViews
    fun init() {
        val mLayoutManager = GridLayoutManager(activity, 1)
        locationRecyclerView.layoutManager = mLayoutManager
        locationRecyclerView.itemAnimator = DefaultItemAnimator()
        locationRecyclerView.adapter = locationAdapter
        locationAdapter.itemClickListener = object : RecyclerViewAdapterBase.OnItemClickListener<Location> {
            override fun onItemClick(item: Location) {
                if (item.locations.isNotEmpty()) {
                    locationsList.addLast(item.locations)
                    applyLocations(item.locations)
                } else {
                    FragmentUtils.add(activity, FeedsFragment_.builder().location(item).build())
                }
            }
        }

        openIndeterminateDialog("[ToDo]Caricamento feed in corso")

        ApiRestCLient.locationsService.getLocations().enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                closeIndeterminateDialog()
                if (response.isSuccessful) {
                    println("posts loaded from API. Locations: " + response.body().result?.locations?.size)
                    val locationsTree = buildLocationsTree(response.body().result?.locations.orEmpty())
                    locationsList.clear()
                    locationsList.add(locationsTree.toMutableList())
                    applyLocations(locationsTree.toMutableList())
                } else {
                    val statusCode = response.code()
                    println("Error code " + statusCode)
                }
            }

            override fun onFailure(call: Call<LocationResponse>?, t: Throwable?) {
                closeIndeterminateDialog()
                println("error loading from API")
            }
        })
    }

    private fun applyLocations(locations: MutableList<Location>) {
        locationAdapter.items = locations
        locationAdapter.notifyDataSetChanged()

        if (!locations.isEmpty()) {
            titleTV.text = locations[0].parent?.name
            titleTV.visibility = if(titleTV.text.isNotBlank()) VISIBLE else GONE
        }

    }

    private fun buildLocationsTree(input: List<Location>): List<Location> {
        val hm = HashMap<Int, Location>()
        var child: Location?
        var mmdParent: Location?

        for (item in input) {

            // ------ Process child ----
            if (!hm.containsKey(item.id)) {
                hm[item.id] = item
            }
            child = hm[item.id]

            // ------ Process Parent ----
            if (item.parentId != 0) {
                if (hm.containsKey(item.parentId)) {
                    mmdParent = hm[item.parentId]
                    child!!.parent = mmdParent
                    mmdParent!!.locations.add(child)
                }
            }

        }

        val dx = ArrayList<Location>()
        hm.values.filterTo(dx) { it.parentId == 0 }
        return dx
    }

    override fun backPressed(): Boolean {
        return if (locationsList.isEmpty() || locationsList.size == 1) {
            FragmentUtils.replace(activity, MainFragment_.builder().build())
            true
        } else {
            locationsList.removeLast()
            applyLocations(locationsList.last)
            false
        }
    }
}