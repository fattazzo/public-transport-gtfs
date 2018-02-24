package com.gmail.fattazzo.publictransportgtfs.fragment.main

import android.support.constraint.ConstraintLayout
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.gmail.fattazzo.gtfsdb.entities.BaseModel
import com.gmail.fattazzo.gtfsdb.entities.Operator
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.ParamsFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EFragment(R.layout.fragment_main)
open class MainFragment : BaseFragment() {

    @ViewById
    lateinit var emptyDbLayout: ConstraintLayout

    private var operatorsCount = 0

    @AfterViews
    fun initViews() {
        operatorsCount = BaseModel.count(Operator::class.java)

        emptyDbLayout.visibility = if (operatorsCount == 0) View.VISIBLE else View.GONE
    }

    @Click
    fun searchFeedsButtonClicked() {
        FragmentUtils.add(activity, ParamsFragment_.builder().build())
    }

    override fun backPressed(): Boolean {
        MaterialDialog.Builder(activity!!)
                .iconRes(R.mipmap.ic_launcher_round)
                .title(R.string.app_name)
                .content(R.string.app_exit_comfirmation)
                .positiveText(android.R.string.yes)
                .negativeText(android.R.string.no)
                .onPositive { _, _ -> activity?.finish() }
                .show()

        return false
    }
}