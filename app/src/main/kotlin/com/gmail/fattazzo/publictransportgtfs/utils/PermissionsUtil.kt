package com.gmail.fattazzo.publictransportgtfs.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

object PermissionsUtil {
    const val PERMISSION_ALL = 1

    private fun doesAppNeedPermissions(): Boolean {
        return android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    @Throws(PackageManager.NameNotFoundException::class)
    private fun getPermissions(context: Context): Array<String> {
        val info = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
        return info.requestedPermissions
    }

    fun askPermissions(activity: Activity) {
        if (doesAppNeedPermissions()) {
            try {
                val permissions = getPermissions(activity)

                if (!checkPermissions(activity, permissions)) {
                    ActivityCompat.requestPermissions(activity, permissions, PERMISSION_ALL)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun checkPermissions(context: Context?, permissions: Array<String>?): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }
}