package com.gmail.fattazzo.publictransportgtfs.fragment.main

import com.afollestad.materialdialogs.MaterialDialog
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.ParamsFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EFragment(R.layout.fragment_main)
open class MainFragment : BaseFragment() {

    @Click
    fun buttonClicked() {
        FragmentUtils.add(activity, ParamsFragment_.builder().build())
    }

    override fun backPressed(): Boolean {
        MaterialDialog.Builder(activity!!)
                .iconRes(R.drawable.info)
                .title(R.string.app_name)
                .content(R.string.app_exit_comfirmation)
                .positiveText(android.R.string.yes)
                .negativeText(android.R.string.no)
                .onPositive { _, _ -> activity?.finish() }
                .show()

        return false
    }
}