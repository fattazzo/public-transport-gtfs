package com.gmail.fattazzo.publictransportgtfs.fragment.about

import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.utils.Utils
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.res.StringRes

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/03/18
 */
@EFragment(R.layout.fragment_about)
open class AboutFragment : BaseFragment() {

    @Bean
    lateinit var utils: Utils

    @StringRes
    lateinit var transitlandUrl: String
    @StringRes
    lateinit var transitlandAddFeedUrl: String
    @StringRes
    lateinit var githubAuthorUrl: String
    @StringRes
    lateinit var githubProjectUrl: String

    @Click
    fun transitlandImageViewClicked() {
        utils.openLink(transitlandUrl)
    }

    @Click
    fun addFeedButtonClicked() {
        utils.openLink(transitlandAddFeedUrl)
    }

    @Click(value = [R.id.githubAuthorImageView, R.id.githubAuthorTV])
    fun githubAuthorUrlClicked() {
        utils.openLink(githubAuthorUrl)
    }

    @Click(value = [R.id.githubProjectImageView, R.id.githubProjectTV])
    fun githubProjectUrlClicked() {
        utils.openLink(githubProjectUrl)
    }

    override fun backPressed(): Boolean {
        activity?.supportFragmentManager?.popBackStack()
        return true
    }
}