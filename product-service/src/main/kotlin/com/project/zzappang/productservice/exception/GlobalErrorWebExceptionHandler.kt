package com.project.zzappang.productservice.exception

import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono


@Component
@Order(-2)
class GlobalErrorWebExceptionHandler(
        private val globalErrorAttributes: GlobalErrorAttributes,
        private val applicationContext: ApplicationContext,
        private val serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(globalErrorAttributes, ResourceProperties(), applicationContext) {
    init {
        super.setMessageWriters(serverCodecConfigurer.writers);
        super.setMessageReaders(serverCodecConfigurer.readers);
    }
    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all(), ExceptionHandlerFunction(globalErrorAttributes))
    }
}

@Component
class ExceptionHandlerFunction(
        private val globalErrorAttributes: GlobalErrorAttributes
): HandlerFunction<ServerResponse> {
    override fun handle(request: ServerRequest): Mono<ServerResponse> {
        val errorPropertiesMap = globalErrorAttributes.getErrorAttributes(request, false)
        return ServerResponse.status(errorPropertiesMap["status"] as HttpStatus)
                .contentType(APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(errorPropertiesMap))
    }
}