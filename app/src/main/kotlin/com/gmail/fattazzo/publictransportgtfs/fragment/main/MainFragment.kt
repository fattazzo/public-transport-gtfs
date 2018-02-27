package com.gmail.fattazzo.publictransportgtfs.fragment.main

import android.widget.RelativeLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.gmail.fattazzo.gtfsdb.entities.BaseModel
import com.gmail.fattazzo.gtfsdb.entities.Operator
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import org.androidannotations.annotations.AfterViews
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
    lateinit var rootLayout: RelativeLayout

    private var operatorsCount = 0

    @AfterViews
    fun initViews() {
        operatorsCount = BaseModel.count(Operator::class.java)

        rootLayout.removeAllViews()
        val lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        if (operatorsCount == 0) {
            rootLayout.addView(MainEmptyView_.build(context),lp)
        } else {
            rootLayout.addView(MainOperatorView_.build(context),lp)
        }
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