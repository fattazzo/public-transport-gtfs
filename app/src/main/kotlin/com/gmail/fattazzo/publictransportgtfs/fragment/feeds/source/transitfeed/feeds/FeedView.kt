package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitfeed.feeds

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.FeedVersion
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.response.FeedVersionResponse
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.rest.ApiRestCLient
import com.gmail.fattazzo.publictransportgtfs.utils.AnimationUtils
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EViewGroup(R.layout.item_feed)
open class FeedView(context: Context?) : BindableView<Feed>(context) {

    @ViewById
    lateinit var nameTV: TextView

    @ViewById
    lateinit var providerTV: TextView

    @ViewById
    lateinit var versionsLayout: LinearLayout

    @ViewById
    lateinit var expandButton: ImageView

    @AfterViews
    fun init() {
        expandButton.setImageResource(R.drawable.arrow_down)
    }

    override fun bind(item: Feed) {

        nameTV.text = item.title
        providerTV.text = item.id.substringBefore("/").replace("-", " ").capitalize()

        if (item.versions.isEmpty()) {
            val feedVersion = FeedVersion()
            feedVersion.import = false
            feedVersion.fileSize = 0
            feedVersion.id = "[ToDo] caricamento in corso..."
            val view = FeedVersionView_.build(context, 0)
            view.bind(feedVersion)
            versionsLayout.addView(view)

            ApiRestCLient.feedVersionsService.getFeedVersions(feed = item.id).enqueue(object : Callback<FeedVersionResponse> {
                override fun onResponse(call: Call<FeedVersionResponse>, response: Response<FeedVersionResponse>) {
                    if (response.isSuccessful) {
                        println("posts loaded from API. Feeds Version: " + response.body().result?.versions?.size)

                        item.versions = response.body().result?.versions.orEmpty()
                        bindVersions(item)
                    } else {
                        val statusCode = response.code()
                        println("Error code " + statusCode)
                    }
                }

                override fun onFailure(call: Call<FeedVersionResponse>?, t: Throwable?) {
                    println("error loading from API")
                }
            })
        } else {
            bindVersions(item)
        }
    }

    private fun bindVersions(feed: Feed) {
        versionsLayout.removeAllViews()
        for ((row, version) in feed.versions.withIndex()) {
            val view = FeedVersionView_.build(context, row)
            view.bind(version)

            versionsLayout.addView(view)
        }
    }

    @Click(value = [R.id.nameTV, R.id.providerTV])
    fun titleClicked() {
        if (versionsLayout.visibility == View.GONE) openVersions() else closeVersions()
    }

    private fun closeVersions() {
        expandButton.startAnimation(AnimationUtils.rotateAnimation(180f, 0f, fillAfter = false))

        versionsLayout.animation = AnimationUtils.fadeAnimation(true)
        versionsLayout.animate()
        versionsLayout.visibility = View.GONE
    }

    private fun openVersions() {
        expandButton.startAnimation(AnimationUtils.rotateAnimation(0f, 180f))

        versionsLayout.animation = AnimationUtils.fadeAnimation(false)
        versionsLayout.visibility = View.VISIBLE
        versionsLayout.animate()
    }

}