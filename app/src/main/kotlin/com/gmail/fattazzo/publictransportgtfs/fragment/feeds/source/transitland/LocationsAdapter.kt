package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.gmail.fattazzo.feedsources.transitland.domain.Location
import com.gmail.fattazzo.publictransportgtfs.R


class LocationsAdapter(private val values: List<Location>, context: Context) : ArrayAdapter<Location>(context, R.layout.item_location_transitland, values) {

    private var inflater: LayoutInflater? = null

    init {
        setDropDownViewResource(R.layout.item_location_transitland)
        inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): Location? {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: inflater!!.inflate(R.layout.item_location_transitland,
                parent, false)

        val nameTV: TextView = view.findViewById(R.id.nameTV)
        nameTV.text = values[position].name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: inflater!!.inflate(R.layout.item_location_transitland,
                parent, false)

        val nameTV: TextView = view.findViewById(R.id.nameTV)
        nameTV.text = values[position].name
        return view
    }
}