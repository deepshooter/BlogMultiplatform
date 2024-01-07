package com.deepshooter.blogmultiplatform.api

import com.deepshooter.blogmultiplatform.data.MongoDB
import com.deepshooter.blogmultiplatform.models.Newsletter
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue

@Api(routeOverride = "subscribe")
suspend fun subscribeNewsletter(context: ApiContext) {
    try {
        val newsletter = context.req.getBody<Newsletter>()
        context.res.setBody(newsletter?.let {
            context.data.getValue<MongoDB>().subscribe(newsletter = it)
        })
    } catch (e: Exception) {
        context.res.setBody(e.message)
    }
}