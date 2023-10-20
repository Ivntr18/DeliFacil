package com.alimsoft.common.core

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.ApplicationInfoFlags
import android.content.pm.PackageManager.NameNotFoundException
import android.content.pm.PackageManager.PackageInfoFlags
import android.content.pm.ResolveInfo
import android.os.Build
import android.os.Bundle

val Context.appVersionName: String?
    get() = getSafePackageInfo()?.versionName

fun Context.getSafePackageInfo(flags: Long = 0): PackageInfo? {
    return try {
        if (Build.VERSION.SDK_INT >= 33) {
            packageManager.getPackageInfo(packageName, PackageInfoFlags.of(flags))
        } else {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, flags.toInt())
        }
    } catch (e: NameNotFoundException) {
        null
    }
}

fun Context.safeQueryIntentActivities(intent: Intent, flags: Long = 0): List<ResolveInfo> {
    return if (Build.VERSION.SDK_INT >= 33) {
        packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(flags))
    } else {
        @Suppress("DEPRECATION")
        packageManager.queryIntentActivities(intent, flags.toInt())
    }
}

fun Context.getSafeAppInfo(flags: Long = 0): ApplicationInfo? {
    return try {
        if (Build.VERSION.SDK_INT >= 33) {
            packageManager.getApplicationInfo(packageName, ApplicationInfoFlags.of(flags))
        } else {
            @Suppress("DEPRECATION")
            packageManager.getApplicationInfo(packageName, flags.toInt())
        }
    } catch (e: NameNotFoundException) {
        null
    }
}

fun Context.getSafeAppMetadata(): Bundle? {
    return getSafeAppInfo(PackageManager.GET_META_DATA.toLong())?.metaData
}
