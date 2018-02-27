package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland

import android.net.Uri
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.gmail.fattazzo.feedsources.transitland.domain.Operator
import com.gmail.fattazzo.feedsources.transitland.domain.response.Meta
import com.gmail.fattazzo.feedsources.transitland.domain.response.OperatorResponse
import com.gmail.fattazzo.feedsources.transitland.rest.ApiRestCLient
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.RecyclerViewAdapterBase
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.feeds.FeedsFragment_
import com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.operators.OperatorAdapter
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @author fattazzo
 *         <p/>
 *         date: 05/02/18
 */
@EFragment(R.layout.fragment_search_operators_results)
open class ResultsFragment : BaseFragment() {

    @InstanceState
    @FragmentArg
    lateinit var searchParams: SearchParams

    @Bean
    lateinit var operatorAdapter: OperatorAdapter

    @ViewById
    lateinit var resultsRecyclerView: RecyclerView

    @ViewById
    lateinit var prevPageButton: Button

    @ViewById
    lateinit var nextPageButton: Button

    @JvmField
    @InstanceState
    var meta: Meta? = null

    @JvmField
    @InstanceState
    var operators: ArrayList<Operator>? = null

    @AfterViews
    fun init() {
        val mLayoutManager = GridLayoutManager(activity, 1)
        resultsRecyclerView.layoutManager = mLayoutManager
        resultsRecyclerView.itemAnimator = DefaultItemAnimator()
        resultsRecyclerView.adapter = operatorAdapter
        operatorAdapter.items = mutableListOf()
        operatorAdapter.itemClickListener = object : RecyclerViewAdapterBase.OnItemClickListener<Operator> {
            override fun onItemClick(item: Operator) {
                val feedsId = ArrayList<String>(item.representedInFeedOnestopIds.orEmpty())
                FragmentUtils.add(activity!!, FeedsFragment_.builder().feedsId(feedsId).build())
            }

        }
        operatorAdapter.notifyDataSetChanged()

        loadResult()
    }

    private fun loadResult() {
        if (operators != null) {
            assignOperators()
            return
        }

        openIndeterminateDialog("[ToDo] Caricamento operatori in corso...")

        val options: MutableMap<String, String> = mutableMapOf()
        options["country"] = searchParams.countryCode
        if (searchParams.location != null && searchParams.location!!.isNotEmpty()) {
            options["state"] = searchParams.location!!.code
        }
        if (!searchParams.name.isNullOrBlank()) {
            options["name"] = searchParams.name!!
        }

        ApiRestCLient.operatorService.getOperators(offset = searchParams.offset,
                perPage = searchParams.perPage,
                options = options).enqueue(object : Callback<OperatorResponse> {
            override fun onResponse(call: Call<OperatorResponse>, response: Response<OperatorResponse>) {
                closeIndeterminateDialog()
                if (response.isSuccessful) {
                    println("operators loaded from API: " + response.body().operators?.size)
                    meta = response.body().meta

                    operators = arrayListOf()
                    operators!!.addAll(response.body().operators.orEmpty())

                    assignOperators()
                } else {
                    val statusCode = response.code()
                    println("Error code " + statusCode)
                }
            }

            override fun onFailure(call: Call<OperatorResponse>?, t: Throwable?) {
                closeIndeterminateDialog()
                println("error loading from API")

                prevPageButton.isEnabled = false
                nextPageButton.isEnabled = false
            }
        })
    }

    private fun assignOperators() {
        operatorAdapter.items = operators.orEmpty().toMutableList()
        operatorAdapter.notifyDataSetChanged()

        prevPageButton.isEnabled = meta?.prev != null
        nextPageButton.isEnabled = meta?.next != null
    }

    @Click
    fun prevPageButtonClicked() {
        if (meta?.prev == null) return

        updateSearchParamFromUri(Uri.parse(meta!!.prev))
        operators = null
        loadResult()
    }

    @Click
    fun nextPageButtonClicked() {
        if (meta?.next == null) return

        updateSearchParamFromUri(Uri.parse(meta!!.next))
        operators = null
        loadResult()
    }

    private fun updateSearchParamFromUri(uri: Uri) {
        val offset = uri.getQueryParameter("offset")
        val perPage = uri.getQueryParameter("per_page")

        searchParams.offset = offset.toInt()
        searchParams.perPage = perPage.toInt()
    }

    override fun backPressed(): Boolean {
        activity?.supportFragmentManager?.popBackStack()
        return true
    }
}