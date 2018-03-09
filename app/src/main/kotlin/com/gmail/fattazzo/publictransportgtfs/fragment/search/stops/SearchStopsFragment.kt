package com.gmail.fattazzo.publictransportgtfs.fragment.search.stops

import android.app.Activity
import android.content.Intent
import android.widget.ListView
import android.widget.SearchView
import com.gmail.fattazzo.gtfsdb.entities.Stop
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import org.androidannotations.annotations.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 09/03/18
 */
@EFragment(R.layout.fragment_search_stops)
open class SearchStopsFragment : BaseFragment(), SearchView.OnQueryTextListener {

    @ViewById
    lateinit var stopsSearchView: SearchView

    @ViewById
    lateinit var resultListView: ListView

    @JvmField
    @InstanceState
    protected var queryString: String? = null

    @Bean
    lateinit var stopsListAdapter: StopsListAdapter

    @JvmField
    @InstanceState
    var stopsFound = arrayListOf<Stop>()

    @AfterViews
    fun init() {
        stopsSearchView.setQuery(queryString, false)
        stopsSearchView.setOnQueryTextListener(this)

        resultListView.adapter = stopsListAdapter
        bindResultView()
    }

    @Background
    open fun searchStops(value: String) {
        stopsFound = arrayListOf()
        stopsFound.addAll(Stop.search(value).orEmpty())
        println("Stops trovati per la ricerca \"$value\": ${stopsFound.orEmpty().size}")
        stopsFound.orEmpty().forEach { println(it.name) }
        bindResultView()
    }

    @UiThread
    open fun bindResultView() {
        stopsListAdapter.items = stopsFound.orEmpty()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        queryString = query
        if (queryString != null && queryString!!.length > 2) {
            searchStops(queryString!!)
            stopsSearchView.clearFocus()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    @ItemClick
    fun resultListViewItemClicked(stop: Stop) {
        stop.selected = !stop.selected
        if (stopsFound.indexOf(stop) != -1) {
            stopsFound.set(stopsFound.indexOf(stop), stop)
            stopsListAdapter.notifyDataSetChanged()
        }

    }

    @Click
    fun resultButtonClicked() {
        val stopsSelected = arrayListOf<Stop>()
        stopsSelected.addAll(stopsFound.filter { it.selected })
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, Intent().putExtra(EXTRA_STOPS, stopsSelected))
        backPressed()
    }

    override fun backPressed(): Boolean {
        activity?.supportFragmentManager?.popBackStack()
        return true
    }

    companion object {
        const val SEARCH_RESULT = 1
        const val EXTRA_STOPS = "extraStops"
    }
}