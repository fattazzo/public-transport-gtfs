package com.gmail.fattazzo.publictransportgtfs.fragment.transitfeed.feeds

import android.content.Context
import android.text.format.DateFormat
import android.text.format.Formatter
import android.util.TypedValue
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.FeedVersion
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EViewGroup(R.layout.item_feed_version)
open class FeedVersionView(context: Context?, var rowId: Int) : BindableView<FeedVersion>(context) {

    @ViewById
    lateinit var nameTV: TextView

    @ViewById
    lateinit var sizeTV: TextView

    @ViewById
    lateinit var downloadButton: ImageButton

    override fun bind(item: FeedVersion) {

        nameTV.text = item.id

        if (item.import) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = 0
            calendar.add(Calendar.SECOND, item.timestamp)

            nameTV.text = DateFormat.format("dd MMMM yyyy", calendar)
            sizeTV.text = Formatter.formatFileSize(context, item.fileSize.toLong())
        }
        downloadButton.visibility = if (item.import) View.VISIBLE else View.GONE

        setBackgroundColor(if (rowId % 2 == 0) themeEvenRowColor else themeOddRowColor)
    }

    private val themeEvenRowColor: Int
        get() {
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.evenRowColor, value, true)
            return value.data
        }

    private val themeOddRowColor: Int
        get() {
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.oddRowColor, value, true)
            return value.data
        }
}