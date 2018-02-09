package com.gmail.fattazzo.publictransportgtfs.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

/**
 * @author fattazzo
 *
 *
 * date: 13/04/17
 */
@EBean(scope = EBean.Scope.Singleton)
open class Utils {

    @RootContext
    internal lateinit var context: Context

    /**
     * Open link in external activity.
     *
     * @param link link to open
     */
    fun openLink(link: String?) {
        if (link != null && link.isNotBlank()) {
            val i = Intent(Intent.ACTION_VIEW)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.data = Uri.parse(link)
            context.startActivity(i)
        }
    }
}
