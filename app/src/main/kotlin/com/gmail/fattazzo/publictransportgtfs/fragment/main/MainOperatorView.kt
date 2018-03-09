package com.gmail.fattazzo.publictransportgtfs.fragment.main

import android.content.Context
import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.gmail.fattazzo.gtfsdb.entities.*
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.activity.maps.MapsActivity_
import org.androidannotations.annotations.*
import java.text.DateFormat

/**
 * @author fattazzo
 *         <p/>
 *         date: 27/02/18
 */
@EViewGroup(R.layout.view_main_operator)
open class MainOperatorView(context: Context?) : ScrollView(context) {

    @ViewById
    lateinit var operatorTV: TextView

    @ViewById
    lateinit var stopsTV: TextView

    @ViewById
    lateinit var routesTV: TextView

    @ViewById
    lateinit var tripsTV: TextView

    @ViewById
    lateinit var agenciesLayout: LinearLayout

    private var operator: Operator? = null
    private var stopsCount = 0
    private var routesCount = 0
    private var tripsCount = 0
    private var agencies = listOf<Agency>()
    private var feedsInfo = listOf<FeedInfo>()

    @AfterViews
    fun initViews() {
        loadData()
    }

    @Background
    open fun loadData() {
        operator = Operator.load()
        stopsCount = BaseModel.count(Stop::class.java)
        routesCount = BaseModel.count(Route::class.java)
        tripsCount = BaseModel.count(Trip::class.java)
        agencies = BaseModel.loadAll(Agency::class.java)
        feedsInfo = BaseModel.loadAll(FeedInfo::class.java)

        updateViews()
    }

    @UiThread
    open fun updateViews() {
        operatorTV.text = operator?.name.orEmpty()
        stopsTV.text = stopsCount.toString()
        routesTV.text = routesCount.toString()
        tripsTV.text = tripsCount.toString()

        agenciesLayout.removeAllViews()
        for (agency in agencies) {
            val agencyTV = TextView(context)
            agencyTV.text = agency.name
            agencyTV.setTypeface(null, Typeface.BOLD);
            agenciesLayout.addView(agencyTV)

            for (feedInfo in feedsInfo) {
                if (agency.feedId == feedInfo.feedId) {
                    val sb = StringBuilder()
                    feedInfo.startDate.let {
                        sb.append("Valido dal ")
                        sb.append(DateFormat.getDateInstance().format(feedInfo.startDate))
                        feedInfo.endDate.let {
                            sb.append(" al ")
                            sb.append(DateFormat.getDateInstance().format(feedInfo.endDate))
                        }
                    }
                    if (sb.isNotEmpty()) {
                        val feedTV = TextView(context)
                        feedTV.text = sb.toString()
                        agenciesLayout.addView(feedTV)
                    }
                    break
                }
            }
        }
    }

    @Click
    internal fun stopsButtonClicked() {
        MapsActivity_.intent(context).start()
    }
}