package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.feeds

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.rest.ApiRestCLient
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import org.androidannotations.annotations.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author fattazzo
 *
 *
 * date: 07/02/18
 */
@EFragment(R.layout.fragment_feeds_transitland)
open class FeedsFragment : BaseFragment() {

    @InstanceState
    @FragmentArg
    lateinit var feedsId: ArrayList<String>

    @ViewById
    lateinit var feedsRecyclerView: RecyclerView

    @Bean
    lateinit var feedsAdapter: FeedsAdapter

    @JvmField
    @InstanceState
    var feeds: ArrayList<Feed>? = null

    @AfterViews
    fun init() {
        val mLayoutManager = GridLayoutManager(this.activity, 1)
        feedsRecyclerView.layoutManager = mLayoutManager
        feedsRecyclerView.itemAnimator = DefaultItemAnimator()
        feedsRecyclerView.adapter = feedsAdapter
        feedsAdapter.items = mutableListOf()
        feedsAdapter.notifyDataSetChanged()

        loadFeeds()
    }

    private fun loadFeeds() {
        if (feeds != null) {
            feedsAdapter.items = feeds.orEmpty().toMutableList()
            feedsAdapter.notifyDataSetChanged()
            return
        }

        for (id in feedsId) {
            feeds = arrayListOf()
            feedsAdapter.items = mutableListOf()
            openIndeterminateDialog("[ToDo] Caricamento feed in corso...")
            ApiRestCLient.feedsService.getFeed(id).enqueue(object : Callback<Feed> {
                override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                    closeIndeterminateDialog()
                    if (response.isSuccessful) {
                        feeds!!.add(response.body())

                        feedsAdapter.items.add(response.body())
                        feedsAdapter.notifyDataSetChanged()
                    } else {
                        val statusCode = response.code()
                        println("Error code " + statusCode)
                    }
                }

                override fun onFailure(call: Call<Feed>?, t: Throwable?) {
                    closeIndeterminateDialog()
                    feeds = null
                }
            })
        }
    }

    override fun backPressed(): Boolean {
        activity?.supportFragmentManager?.popBackStack()
        return true
    }
}
