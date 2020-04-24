package com.project.zzappang.productservice.exception

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.http.HttpStatus
import java.util.LinkedHashMap
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ServerWebInputException

@Component
class GlobalErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, includeStackTrace: Boolean): Map<String, Any> {
        return assembleError(request)
    }

    private fun assembleError(request: ServerRequest): Map<String, Any> {
        val errorAttributes = LinkedHashMap<String, Any>()
        when (val error = getError(request)) {
            is NotFoundException -> {
                errorAttributes["status"] = HttpStatus.NOT_FOUND
                errorAttributes["message"] = error.message
            }
            is ServerWebInputException -> {
                errorAttributes["status"] = HttpStatus.BAD_REQUEST
                errorAttributes["message"] = error.message
            }
            else -> {
                errorAttributes["status"] = HttpStatus.INTERNAL_SERVER_ERROR
                errorAttributes["message"] = error.message ?: "unknown internal server error"
                errorAttributes["stackTrace"] = error.stackTrace
            }
        }

        return errorAttributes
    }
}