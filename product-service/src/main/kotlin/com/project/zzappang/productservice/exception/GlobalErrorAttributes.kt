package com.project.zzappang.productservice.exception

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.http.HttpStatus
import java.util.LinkedHashMap
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class GlobalErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, includeStackTrace: Boolean): Map<String, Any> {
        return assembleError(request)
    }

    private fun assembleError(request: ServerRequest): Map<String, Any> {
        val errorAttributes = LinkedHashMap<String, Any>()
        val error = getError(request)
        if (error is ProductNotFoundException) {
            errorAttributes["status"] = HttpStatus.NOT_FOUND
            errorAttributes["message"] = error.message
        } else {
            errorAttributes["status"] = HttpStatus.INTERNAL_SERVER_ERROR
            errorAttributes["message"] = "INTERNAL SERVER ERROR"
        }
        return errorAttributes
    }
}