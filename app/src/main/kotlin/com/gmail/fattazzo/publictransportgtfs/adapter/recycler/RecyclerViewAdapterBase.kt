package com.gmail.fattazzo.publictransportgtfs.adapter.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import java.util.*


abstract class RecyclerViewAdapterBase<T, V : BindableView<T>> : RecyclerView.Adapter<ViewWrapper<T, V>>() {

    interface OnItemClickListener<in T> {
        fun onItemClick(item: T)
    }

    var items: MutableList<T> = ArrayList()
        set(value) {
            field = value
            lastPosition = -1
        }

    private var lastPosition: Int = -1

    var itemClickListener: OnItemClickListener<T>? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<T, V> {
        return ViewWrapper(onCreateItemView(parent, viewType))
    }

    override fun onBindViewHolder(holder: ViewWrapper<T, V>, position: Int) {
        val view = holder.view
        view.bind(items[position])
        view.setOnClickListener {
            if (itemClickListener != null) {
                itemClickListener!!.onItemClick(items[position])
            }
        }

        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V
}