package com.gmail.fattazzo.publictransportgtfs.adapter.recycler

import android.content.Context
import android.support.constraint.ConstraintLayout

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
abstract class BindableView<in T>(context: Context?) : ConstraintLayout(context) {

    abstract fun bind(item: T)
}