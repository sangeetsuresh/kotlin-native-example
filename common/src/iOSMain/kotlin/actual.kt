package com.sangeetsuresh.kotlincommon

import kotlinx.cinterop.staticCFunction
import platform.Foundation.NSOperationQueue
import platform.darwin.dispatch_async_f
import kotlin.native.concurrent.DetachedObjectGraph
import kotlin.native.concurrent.attach
import kotlin.native.concurrent.freeze


inline fun <reified T> executeAsync(queue: NSOperationQueue, crossinline producerConsumer: () -> Pair<T?, (T?) -> Unit>) {
    dispatch_async_f(queue.underlyingQueue, DetachedObjectGraph {
        producerConsumer().freeze()
    }.asCPointer(), staticCFunction { it ->
        val result = DetachedObjectGraph<Pair<T, (T) -> Unit>>(it).attach()
        result.second(result.first)
    })
}