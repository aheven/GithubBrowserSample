package heven.holt.jetpack.github.util

import timber.log.Timber

/**
 *Time:2020/9/27
 *Author:HevenHolt
 *Description:Timber日志
 */
class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

    }
}