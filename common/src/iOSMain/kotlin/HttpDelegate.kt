package com.sangeetsuresh.kotlincommon

import platform.Foundation.NSHTTPURLResponse
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.NSURLConnection
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.sendSynchronousRequest
import platform.Foundation.setHTTPMethod

actual class HttpDelegate {
    actual companion object {
        actual fun fetchUrl(url: String, block: ((String?) -> Unit)) {
            executeAsync(NSOperationQueue.mainQueue) {
                var response:String?=null
                try {
                    val request = NSMutableURLRequest().init()
                    request?.setHTTPMethod("GET")
                    request?.setURL(NSURL.URLWithString(url))

                    val responseCode: NSHTTPURLResponse? = null
                    val oResponseData = NSURLConnection.sendSynchronousRequest(request!!, null, error = null);
                    if (responseCode?.statusCode != 200L) {
                    }
                    response = NSString.create(data = oResponseData!!, encoding = NSUTF8StringEncoding)!! as String
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
                Pair(response, { result: String? ->
                    println(result)
                    block.invoke(result)
                })
            }
        }
    }
}