package com.gmail.fattazzo.publictransportgtfs.adapter.recycler

import android.support.v7.widget.RecyclerView

class ViewWrapper<T, out V : BindableView<T>>(val view: V) : RecyclerView.ViewHolder(view)