package heven.holt.jetpack.github

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import heven.holt.jetpack.github.util.CrashReportingTree
import timber.log.Timber

/**
 *Time:2020/9/25
 *Author:HevenHolt
 *Description:
 */
@HiltAndroidApp
class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}