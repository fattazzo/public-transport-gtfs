package com.gmail.fattazzo.publictransportgtfs.utils

import android.view.animation.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 27/10/17
 */
class AnimationUtils {

    companion object {

        fun rotateAnimation(fromDegrees : Float, toDegrees : Float, duration : Long = 500, fillAfter: Boolean = true) : Animation {
            val rotate: Animation = RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotate.startOffset = 0
            rotate.interpolator = LinearInterpolator()
            rotate.duration = duration
            rotate.fillAfter = fillAfter
            return rotate
        }

        fun fadeAnimation(out : Boolean, duration: Long = 500) : Animation {
            val fade = if(out) AlphaAnimation(1f, 0f) else AlphaAnimation(0f, 1f)
            fade.interpolator = AccelerateInterpolator()
            fade.startOffset = 0
            fade.duration = duration
            return fade
        }
    }
}