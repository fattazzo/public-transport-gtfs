package com.gmail.fattazzo.publictransportgtfs.fragment

import android.content.Context
import android.support.v4.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.gmail.fattazzo.publictransportgtfs.R
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

    open fun openIndeterminateDialog(title: String) {
        openIndeterminateDialog(title, activity!!)
    }

    open fun openIndeterminateDialog(titleResId: Int) {
        openIndeterminateDialog(resources.getString(titleResId), activity!!)
    }

    @UiThread(propagation = UiThread.Propagation.ENQUEUE)
    open fun openIndeterminateDialog(title: String, context: Context) {
        if (dialog == null || !dialog!!.isShowing) {
            dialog = MaterialDialog.Builder(context)
                    .title(title)
                    .content(R.string.dialog_wait_content)
                    .progress(true, 0)
                    .cancelable(false)
                    .progressIndeterminateStyle(true)
                    .build()
            dialog!!.show()
        }
    }

    @UiThread(propagation = UiThread.Propagation.ENQUEUE)
    open fun closeIndeterminateDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }
}