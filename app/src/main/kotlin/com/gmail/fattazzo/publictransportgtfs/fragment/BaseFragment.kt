package com.gmail.fattazzo.publictransportgtfs.fragment

import android.content.Context
import android.support.v4.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.UiThread


/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EFragment
open class BaseFragment : Fragment() {

    private var dialog: MaterialDialog? = null

    open fun backPressed(): Boolean {
        return false
    }

    @UiThread
    open fun openIndeterminateDialog(title: String) {
        openIndeterminateDialog(title, activity)
    }

    @UiThread
    open fun openIndeterminateDialog(title: String, context: Context) {
        dialog = MaterialDialog.Builder(context)
                .title(title)
                .content("[ToDo]Attendere prego...")
                .progress(true, 0)
                .cancelable(false)
                .progressIndeterminateStyle(true)
                .build()
        dialog!!.show()
    }

    @UiThread
    open fun closeIndeterminateDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }
}