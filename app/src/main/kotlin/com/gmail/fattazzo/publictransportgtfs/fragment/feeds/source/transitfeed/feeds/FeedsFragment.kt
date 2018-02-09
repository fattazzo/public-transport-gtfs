package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitfeed.feeds

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.Location
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.response.FeedResponse
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.rest.ApiRestCLient
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import org.androidannotations.annotations.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EFragment(R.layout.fragment_feeds)
open class FeedsFragment : BaseFragment() {

    @FragmentArg
    lateinit var location: Location

    @Bean
    lateinit var feedAdapter: FeedAdapter

    @ViewById
    lateinit var feedsRecyclerView: RecyclerView

    @AfterViews
    fun init() {
        val mLayoutManager = GridLayoutManager(activity, 1)
        feedsRecyclerView.layoutManager = mLayoutManager
        feedsRecyclerView.itemAnimator = DefaultItemAnimator()
        feedsRecyclerView.adapter = feedAdapter
        feedAdapter.items = mutableListOf()
        feedAdapter.notifyDataSetChanged()

        loadFeeds()
    }

    private fun loadFeeds() {
        openIndeterminateDialog("[ToDo]Caricamento feed in corso")

        ApiRestCLient.feedsService.getFeeds(locationId = location.id).enqueue(object : Callback<FeedResponse> {
            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
                closeIndeterminateDialog()
                if (response.isSuccessful) {
                    println("posts loaded from API. Feeds: " + response.body().result?.feeds?.size)

                    loadFeedVersions(response.body().result?.feeds.orEmpty())
                    feedAdapter.items = response.body().result?.feeds.orEmpty().toMutableList()
                    feedAdapter.notifyDataSetChanged()
                } else {
                    val statusCode = response.code()
                    println("Error code " + statusCode)
                }
            }

            override fun onFailure(call: Call<FeedResponse>?, t: Throwable?) {
                closeIndeterminateDialog()
                println("error loading from API")
            }
        })
    }

    private fun loadFeedVersions(feeds: List<Feed>) {
        feeds.forEach { feed ->
            /**
            openIndeterminateDialog("[ToDo] Caricamento versioni in corso...")
            ApiRestCLient.feedVersionsService.getFeedVersions(feed = feed.id).enqueue(object : Callback<FeedVersionResponse> {
            override fun onResponse(call: Call<FeedVersionResponse>, response: Response<FeedVersionResponse>) {
            closeIndeterminateDialog()
            if (response.isSuccessful) {
            println("posts loaded from API. Feeds Version: " + response.body().result?.versions?.size)

            feed.versions = response.body().result?.versions.orEmpty()
            feedAdapter.items.add(feed)
            feedAdapter.notifyDataSetChanged()
            } else {
            val statusCode = response.code()
            println("Error code " + statusCode)
            }
            }

            override fun onFailure(call: Call<FeedVersionResponse>?, t: Throwable?) {
            closeIndeterminateDialog()
            println("error loading from API")
            }
            })
             **/
        }
    }

    override fun backPressed(): Boolean {
        activity!!.supportFragmentManager.popBackStack()
        return true
    }
}