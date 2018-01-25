package com.gmail.fattazzo.publictransportgtfs.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.gmail.fattazzo.publictransportgtfs.R

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
object FragmentUtils {

    enum class AnimationType { NONE, RIGHT_TO_LEFT, LEFT_TO_RIGHT, FADE_IN, FADE_OUT }

    fun replace(activity: Activity, fragment: Fragment, containerResId: Int = R.id.container, animationType: AnimationType = AnimationType.NONE) {
        val transaction = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        when (animationType) {
            AnimationType.RIGHT_TO_LEFT -> transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            AnimationType.LEFT_TO_RIGHT -> transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
            AnimationType.FADE_IN -> transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            AnimationType.FADE_OUT -> transaction.setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
            else -> {
                // No animation
            }
        }

        transaction.replace(containerResId, fragment).addToBackStack(null).commit()
    }

    fun add(activity: Activity, fragment: Fragment, containerResId: Int = R.id.container) {
        val transaction = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        transaction.add(containerResId, fragment).addToBackStack(null).commit()
    }
}