package com.gmail.fattazzo.publictransportgtfs.fragment.main

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.ParamsFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup

/**
 * @author fattazzo
 *         <p/>
 *         date: 27/02/18
 */
@EViewGroup(R.layout.view_main_empty)
open class MainEmptyView(context: Context?) : ConstraintLayout(context) {

    @Click
    fun searchFeedsButtonClicked() {
        FragmentUtils.replace(context as Activity?, ParamsFragment_.builder().build())
    }
}