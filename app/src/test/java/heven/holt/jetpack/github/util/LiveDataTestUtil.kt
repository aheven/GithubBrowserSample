package heven.holt.jetpack.github.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 *Time:2020/9/27
 *Author:HevenHolt
 *Description:
 */
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserver: () -> Unit = {}
): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T>{
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    observeForever(observer)

    afterObserver.invoke()

    //超时不等待LiveData返回数据
    if (!latch.await(time,timeUnit)){
        removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }
    return data
}